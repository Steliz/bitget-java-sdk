package com.bitget.openapi.service.v2.spot;

import com.bitget.openapi.api.v2.SpotOrderApi;
import com.bitget.openapi.common.client.ApiClient;
import com.bitget.openapi.common.constant.TypeConst;
import com.bitget.openapi.common.domain.spot.order.OrderInfo;
import com.bitget.openapi.common.domain.spot.order.PlaceOrderRequest;
import com.bitget.openapi.common.domain.spot.order.PlaceOrderResponse;
import com.bitget.openapi.common.utils.ResponseUtils;
import com.bitget.openapi.dto.response.ResponseResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpotOrderService {

    private final SpotOrderApi spotOrderApi;
    private final Gson gson = new Gson();

    public SpotOrderService(ApiClient client){
        spotOrderApi = client.create(SpotOrderApi.class);
    }

    public ResponseResult<PlaceOrderResponse> placeOrder(PlaceOrderRequest body) throws IOException {
        Map<String, String> paramMap = gson.fromJson(gson.toJson(body), TypeConst.DEFUALT_PARAMS_TYPE);

        var response = Objects.requireNonNull(spotOrderApi.placeOrder(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult batchOrders(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.batchOrders(paramMap).execute().body());
    }

    public ResponseResult cancelOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.cancelOrder(paramMap).execute().body());
    }

    public ResponseResult batchCancelOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.batchCancelOrder(paramMap).execute().body());
    }

    public ResponseResult<List<OrderInfo>> orderInfo(String orderId, boolean isCustomId) throws IOException {
        String keyName = isCustomId ? "clientOid" : "orderId";
        Map<String, String> paramMap = Map.of(keyName, orderId);

        var response = Objects.requireNonNull(spotOrderApi.orderInfo(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult unfilledOrders(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.unfilledOrders(paramMap).execute().body());
    }

    public ResponseResult historyOrders(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.historyOrders(paramMap).execute().body());
    }

    public ResponseResult fills(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.fills(paramMap).execute().body());
    }



    public ResponseResult placePlanOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.placePlanOrder(paramMap).execute().body());
    }

    public ResponseResult cancelPlanOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.cancelPlanOrder(paramMap).execute().body());
    }

    public ResponseResult currentPlanOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.currentPlanOrder(paramMap).execute().body());
    }

    public ResponseResult historyPlanOrder(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.historyPlanOrder(paramMap).execute().body());
    }



    public ResponseResult traderOrderCloseTracking(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.traderOrderCloseTracking(paramMap).execute().body());
    }

    public ResponseResult traderOrderCurrentTrack(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.traderOrderCurrentTrack(paramMap).execute().body());
    }

    public ResponseResult traderOrderHistoryTrack(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotOrderApi.traderOrderHistoryTrack(paramMap).execute().body());
    }

}
