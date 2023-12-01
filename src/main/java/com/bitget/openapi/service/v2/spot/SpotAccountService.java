package com.bitget.openapi.service.v2.spot;

import com.bitget.openapi.api.v2.SpotAccountApi;
import com.bitget.openapi.common.client.ApiClient;
import com.bitget.openapi.common.domain.spot.account.AccountAsset;
import com.bitget.openapi.common.utils.ResponseUtils;
import com.bitget.openapi.dto.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: bitget
 * @Date: 2021-05-31 15:08
 * @DES:
 */
public class SpotAccountService {

    private final SpotAccountApi spotAccountApi;

    public SpotAccountService(ApiClient client) {
        spotAccountApi = client.create(SpotAccountApi.class);
    }

    public ResponseResult info() throws IOException {
        return ResponseUtils.handleResponse(spotAccountApi.info().execute().body());
    }

    public ResponseResult<List<AccountAsset>> assets() throws IOException {
        return assets(null);
    }

    public ResponseResult<List<AccountAsset>> assets(String coin) throws IOException {
        HashMap<String , String> paramMap = new HashMap<>();
        if (StringUtils.isNotEmpty(coin)) {
            paramMap.put("coin", coin);
        }

        var response = Objects.requireNonNull(spotAccountApi.assets(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult bills(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotAccountApi.bills(paramMap).execute().body());
    }

    public ResponseResult transferRecords(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotAccountApi.transferRecords(paramMap).execute().body());
    }
}
