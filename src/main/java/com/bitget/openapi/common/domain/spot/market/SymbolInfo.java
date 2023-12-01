package com.bitget.openapi.common.domain.spot.market;

public record SymbolInfo(
        String symbol,
        String baseCoin,
        String quoteCoin,
        String minTradeAmount,
        String maxTradeAmount,
        String takerFeeRate,
        String makerFeeRate,
        String pricePrecision,
        String quantityPrecision,
        String quotePrecision,
        String minTradeUSDT,
        String status,
        String butLimitPriceRatio,
        String sellLimitRatio
) {

}
