package com.bitget.openapi.common.domain.spot.account;

public record AccountAsset(
        String coin,
        String available,
        String frozen,
        String locked,
        String limitAvailable,
        String uTime
) {

}
