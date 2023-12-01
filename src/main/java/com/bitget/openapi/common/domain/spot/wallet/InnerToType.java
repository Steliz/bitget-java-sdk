package com.bitget.openapi.common.domain.spot.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Type of address for internal withdrawals
 */
@Getter
@AllArgsConstructor
public enum InnerToType {
    EMAIL("email"),
    MOBILE("mobile"),
    UID("uid")
    ;

    private final String name;
}
