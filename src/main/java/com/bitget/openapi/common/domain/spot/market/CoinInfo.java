package com.bitget.openapi.common.domain.spot.market;

import java.util.List;

public record CoinInfo(
        String coinId,
        String coin,
        Boolean transfer,
        List<Chain> chains
) {
}
