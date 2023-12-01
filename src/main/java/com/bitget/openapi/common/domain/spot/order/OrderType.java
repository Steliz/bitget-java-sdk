package com.bitget.openapi.common.domain.spot.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderType {

    LIMIT("limit"),
    MARKET("market")
    ;

    private final String name;
}
