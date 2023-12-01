package com.bitget.openapi.common.domain.spot.order;

import lombok.Builder;

@Builder
public record PlaceOrderRequest(
        String symbol,
        String side,
        String orderType,
        String force,
        String price,
        String size,
        String clientOid
) {

}
