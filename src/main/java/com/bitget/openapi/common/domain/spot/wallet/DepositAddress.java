package com.bitget.openapi.common.domain.spot.wallet;

public record DepositAddress(String address, String chain, String coin, String tag, String url) {
}
