package com.bitget.openapi.common.domain.spot.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    INIT("init"),
    LIVE("live"),
    NEW("new"),
    PARTIALLY_FILLED("partially_filled"),
    FILLED("filled"),
    CANCELED("canceled")
    ;

    private final String name;
}
