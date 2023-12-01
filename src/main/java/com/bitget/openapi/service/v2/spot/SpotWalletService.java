package com.bitget.openapi.service.v2.spot;

import com.bitget.openapi.api.v2.SpotWalletApi;
import com.bitget.openapi.common.client.ApiClient;
import com.bitget.openapi.common.constant.TypeConst;
import com.bitget.openapi.common.domain.spot.wallet.DepositAddress;
import com.bitget.openapi.common.domain.spot.wallet.WithdrawalRequest;
import com.bitget.openapi.common.domain.spot.wallet.WithdrawalResponse;
import com.bitget.openapi.common.utils.ResponseUtils;
import com.bitget.openapi.dto.response.ResponseResult;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpotWalletService {

    private final SpotWalletApi spotWalletApi;
    private final Gson gson = new Gson();

    public SpotWalletService(ApiClient client){
        spotWalletApi = client.create(SpotWalletApi.class);
    }

    public ResponseResult transfer(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotWalletApi.transfer(paramMap).execute().body());
    }

    public ResponseResult<DepositAddress> depositAddress(String coin) throws IOException {
        return depositAddress(coin, null);
    }

    public ResponseResult<DepositAddress> depositAddress(String coin, String chain) throws IOException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("coin", coin);
        if (StringUtils.isNotEmpty(chain)) {
            paramMap.put("chain", chain);
        }

        var response = Objects.requireNonNull(spotWalletApi.depositAddress(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult<WithdrawalResponse> withdrawal(WithdrawalRequest body) throws IOException {
        Map<String, String> paramMap = gson.fromJson(gson.toJson(body), TypeConst.DEFUALT_PARAMS_TYPE);

        var response = Objects.requireNonNull(spotWalletApi.withdrawal(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult withdrawalRecords(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotWalletApi.withdrawalRecords(paramMap).execute().body());
    }

    public ResponseResult depositRecords(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotWalletApi.depositRecords(paramMap).execute().body());
    }
}
