package com.bitget.openapi.common.domain.spot.market;

import java.util.List;

public record OrderBook(List<List<String>> asks, List<List<String>> bids, String ts) {
}
