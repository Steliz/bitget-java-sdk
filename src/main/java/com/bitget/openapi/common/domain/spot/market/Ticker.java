package com.bitget.openapi.common.domain.spot.market;

public record Ticker(
        String symbol,
        String high24h,
        String open,
        String lastPr,
        String low24h,
        String quoteVolume,
        String baseVolume,
        String usdtVolume,
        String bidPr,
        String askPr,
        String bidSz,
        String askSz,
        String openUtc,
        String ts,
        String changeUtc24h,
        String change24h
) {

}
