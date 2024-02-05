package com.bitget.openapi.common.client;

import com.bitget.openapi.common.constant.HttpHeader;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.domain.ProxyInfo;
import com.bitget.openapi.common.enums.SignTypeEnum;
import com.bitget.openapi.common.utils.SignatureUtils;
import com.bitget.openapi.dto.response.ResponseResult;
import com.google.gson.Gson;
import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author bitget-sdk-team
 * @date 2019-01-15
 */
public class ApiClient {

    private final Retrofit retrofit;

    /**
     * 超时时间 (google translate: timeout)
     */
    private final ClientParameter parameter;

    ApiClient(ClientParameter parameter) {
        this.parameter = parameter;
        retrofit = new Retrofit.Builder()
                .baseUrl(parameter.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient(parameter.getProxyInfo(), parameter.isUseProxy()))
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }

    private OkHttpClient httpClient(ProxyInfo proxyInfo, boolean useProxy) {
        if (useProxy) {
            return createClientWithProxy(proxyInfo);
        } else {
            return createClientWithoutProxy();
        }
    }

    private OkHttpClient createClientWithProxy(ProxyInfo proxyInfo) {
        Properties systemProperties = System.getProperties();
        String host = systemProperties.getProperty("https.proxyHost");
        String port = systemProperties.getProperty("https.proxyPort");
        Authenticator authenticator = Authenticator.JAVA_NET_AUTHENTICATOR;

        if (proxyInfo != null) {
            host = proxyInfo.ip();
            port = proxyInfo.port();

            String credential = Credentials.basic(proxyInfo.login(), proxyInfo.password());
            authenticator = (route, response) -> {
                return response.request().newBuilder().header("Proxy-Authorization", credential).build();
            };
        }

        return new OkHttpClient.Builder()
                .addInterceptor(new SignInterceptor(parameter))
                .addInterceptor(new HttpStatusInterceptor())
                .connectTimeout(parameter.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(parameter.getTimeout(), TimeUnit.SECONDS)
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, Integer.parseInt(port))))
                .proxyAuthenticator(authenticator)
                .build();
    }

    private OkHttpClient createClientWithoutProxy() {
        return new OkHttpClient.Builder()
                .addInterceptor(new SignInterceptor(parameter))
                .addInterceptor(new HttpStatusInterceptor())
                .connectTimeout(parameter.getTimeout(), TimeUnit.SECONDS)
                .readTimeout(parameter.getTimeout(), TimeUnit.SECONDS)
                .build();
    }

    /**
     * 签名过滤器 (google translate: signature filter)
     */
    private class SignInterceptor implements Interceptor {

        private final ClientParameter clientParameter;

        SignInterceptor(ClientParameter clientParameter) {
            this.clientParameter = clientParameter;
        }

        @Override
        public Response intercept(Chain chain) {
            Request original = chain.request();
            String timestamp = String.valueOf(System.currentTimeMillis());
            String contentType = "application/json";
            try {
                if (StringUtils.isEmpty(clientParameter.getApiKey())) {
                    return chain.proceed(original);
                }
                String sign = SignatureUtils.generate(timestamp,
                        original.method(),
                        original.url().url().getPath(),
                        original.url().encodedQuery(),
                        original.body() == null ? "" : bodyToString(original),
                        clientParameter.getSecretKey());
                if (SignTypeEnum.RSA == clientParameter.getSignType()) {
                    sign = SignatureUtils.restGenerateRsaSignature(timestamp,
                            original.method(),
                            original.url().url().getPath(),
                            original.url().encodedQuery(),
                            original.body() == null ? "" : bodyToString(original),
                            clientParameter.getSecretKey());
                }

                String localFormat = "locale=%s";
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader(HttpHeader.ACCESS_KEY, clientParameter.getApiKey())
                        .addHeader(HttpHeader.ACCESS_PASSPHRASE, clientParameter.getPassphrase())
                        .addHeader(HttpHeader.ACCESS_SIGN, sign)
                        .addHeader(HttpHeader.CONTENT_TYPE, contentType)
                        .addHeader(HttpHeader.COOKIE, String.format(localFormat, clientParameter.getLocale()))
                        .addHeader(HttpHeader.LOCALE, clientParameter.getLocale())
                        .addHeader(HttpHeader.ACCESS_TIMESTAMP, timestamp);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private String bodyToString(Request request) {
            try {
                Request copy = request.newBuilder().build();
                Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * http 请求返回状态过滤器 (google translate: http request return status filter)
     */
    private class HttpStatusInterceptor implements Interceptor {
        private final Gson gson = new Gson();

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (response.isSuccessful()) {
                return response;
            }

            if (response.body() == null) {
                throw new RuntimeException("empty response body httpCode:" + response.code());
            }

            try {
                ResponseResult bizResponse = gson.fromJson(response.body().string(), ResponseResult.class);
                bizResponse.setHttpCode(String.valueOf(response.code()));
                MediaType contentType = response.body().contentType();
                ResponseBody body = ResponseBody.create(contentType, gson.toJson(bizResponse));
                return response.newBuilder().code(200).body(body).build();
            } catch (Exception e) {
                throw new RuntimeException("parse response error:" + e.getMessage());
            }
        }
    }
}
