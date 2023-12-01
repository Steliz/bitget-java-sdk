package com.bitget.openapi.api.v2;

import com.bitget.openapi.common.domain.spot.wallet.DepositAddress;
import com.bitget.openapi.common.domain.spot.wallet.WithdrawalResponse;
import com.bitget.openapi.dto.response.ResponseResult;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface SpotWalletApi {

    @POST("/api/v2/spot/wallet/transfer")
    Call<ResponseResult> transfer(@QueryMap Map<String, String> paramMap);

    @GET("/api/v2/spot/wallet/deposit-address")
    Call<ResponseResult<DepositAddress>> depositAddress(@QueryMap Map<String, String> paramMap);

    @POST("/api/v2/spot/wallet/withdrawal")
    Call<ResponseResult<WithdrawalResponse>> withdrawal(@Body Map<String, String> body);

    @GET("/api/v2/spot/wallet/withdrawal-records")
    Call<ResponseResult> withdrawalRecords(@QueryMap Map<String, String> paramMap);

    @GET("/api/v2/spot/wallet/deposit-records")
    Call<ResponseResult> depositRecords(@QueryMap Map<String, String> paramMap);
}
