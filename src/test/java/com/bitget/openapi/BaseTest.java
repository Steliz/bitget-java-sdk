package com.bitget.openapi;

import com.bitget.openapi.common.client.BitgetRestClient;
import com.bitget.openapi.common.domain.ClientParameter;
import com.bitget.openapi.common.enums.SignTypeEnum;
import com.bitget.openapi.common.enums.SupportedLocaleEnum;
import org.junit.After;
import org.junit.Before;

/**
 * @author bitget-sdk-team
 * @date 2019-01-15
 */
public class BaseTest {

    /**
     * 用户 apiKey 替换成自己的
     */
    private final String apiKey = "bg_a737f38f435e3549df91c8cd664e078e";

    /**
     * 用户 secretKey 替换成自己的
     */
    private final String secretKey = "a8a2df1517603d5a417d0d84a2c2ba8320f6977e4fa65ee81b0cfbb4e9b55354";

    /**
     * 口令 替换成自己的
     */
    private final String passphrase = "EPdlcm3131";

    /**
     * bitget open api 根路径
     */
    private final String baseUrl = "https://api.bitget.com";

    private final ClientParameter parameter = ClientParameter.builder()
            .apiKey(apiKey)
            .secretKey(secretKey)
            .passphrase(passphrase)
            .baseUrl(baseUrl)
            //.signType(SignTypeEnum.RSA) // 如果你的apikey是RSA类型则主动设置
            .locale(SupportedLocaleEnum.EN_US.getName())
            .build();
    public BitgetRestClient bitgetRestClient;

    @Before
    public void setup() {
        bitgetRestClient = BitgetRestClient.builder().configuration(parameter).build();
    }

    @After
    public void tearDown() {
    }
}
