package com.bitget.openapi.common.domain.spot.order;

public record OrderInfo(
        String userId,
        String symbolId,
        String orderId,
        String clientOid,
        String price,
        String size,
        OrderType orderType,
        OrderSide side,
        OrderStatus status,
        String priceAvg,
        String baseVolume,
        String quoteVolume,
        String enterPointSource,
        String cTime,
        String uTime,
        String feeDetail,
        String orderSource
) {
}
