package com.bitget.openapi.common.domain.spot.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransferType {
    ON_CHAIN("on_chain"),
    INTERNAL("internal_transfer")
    ;

    private final String name;
}
