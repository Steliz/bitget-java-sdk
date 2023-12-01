package com.bitget.openapi.service.v2.spot;

import com.bitget.openapi.api.v2.SpotMarketApi;
import com.bitget.openapi.common.client.ApiClient;
import com.bitget.openapi.common.domain.spot.market.CoinInfo;
import com.bitget.openapi.common.domain.spot.market.OrderBook;
import com.bitget.openapi.common.domain.spot.market.SymbolInfo;
import com.bitget.openapi.common.domain.spot.market.Ticker;
import com.bitget.openapi.common.exception.BitgetApiException;
import com.bitget.openapi.common.utils.ResponseUtils;
import com.bitget.openapi.dto.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SpotMarketService {

    private final SpotMarketApi spotMarketApi;

    public SpotMarketService(ApiClient client) {
        spotMarketApi = client.create(SpotMarketApi.class);
    }

    public ResponseResult<List<CoinInfo>> coins() throws IOException {
        return coins(null);
    }

    public ResponseResult<List<CoinInfo>> coins(String coin) throws IOException {
        Map<String , String> paramMap = new HashMap<>();
        if (StringUtils.isNotEmpty(coin)) {
            paramMap.put("coin", coin);
        }

        var response = Objects.requireNonNull(spotMarketApi.coins(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult<List<SymbolInfo>> symbols() throws IOException {
        return symbols(new HashMap<>());
    }

    public ResponseResult<List<SymbolInfo>> symbols(Map<String,String> paramMap) throws IOException {
        var response = Objects.requireNonNull(spotMarketApi.symbols(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult fills(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotMarketApi.fills(paramMap).execute().body());
    }

    public ResponseResult<OrderBook> orderBook(String symbol) throws IOException {
        return orderBook(symbol, 0);
    }

    public ResponseResult<OrderBook> orderBook(String symbol, int limit) throws IOException {
        Map<String , String> paramMap = new HashMap<>();
        paramMap.put("symbol", symbol);

        if (limit > 0) {
            if (limit > 150) {
                throw new BitgetApiException("\"limit\" parameter must be less than 150");
            }
            paramMap.put("limit", String.valueOf(limit));
        }

        var response = Objects.requireNonNull(spotMarketApi.orderbook(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult<List<Ticker>> tickers() throws IOException {
        return tickers(null);
    }

    public ResponseResult<List<Ticker>> tickers(String symbol) throws IOException {
        Map<String , String> paramMap = new HashMap<>();
        if (!StringUtils.isEmpty(symbol)) {
            paramMap.put("symbol", symbol);
        }

        var response = Objects.requireNonNull(spotMarketApi.tickers(paramMap).execute().body());
        return ResponseUtils.handleResponse(response);
    }

    public ResponseResult candles(Map<String,String> paramMap) throws IOException {
        return ResponseUtils.handleResponse(spotMarketApi.candles(paramMap).execute().body());
    }
}
