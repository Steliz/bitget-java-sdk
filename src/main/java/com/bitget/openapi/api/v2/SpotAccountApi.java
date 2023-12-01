package com.bitget.openapi.api.v2;

import com.bitget.openapi.common.domain.spot.account.AccountAsset;
import com.bitget.openapi.dto.response.ResponseResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.List;
import java.util.Map;

public interface SpotAccountApi {

    @GET("/api/v2/spot/account/info")
    Call<ResponseResult> info();

    @GET("/api/v2/spot/account/assets")
    Call<ResponseResult<List<AccountAsset>>> assets(@QueryMap Map<String, String> paramMap);

    @GET("/api/v2/spot/account/bills")
    Call<ResponseResult> bills(@QueryMap Map<String, String> paramMap);

    @GET("/api/v2/spot/account/transferRecords")
    Call<ResponseResult> transferRecords(@QueryMap Map<String, String> paramMap);
}
