package com.bitget.openapi.common.domain.spot.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSide {
    SELL("sell"),
    BUY("buy")
    ;

    private final String name;
}
