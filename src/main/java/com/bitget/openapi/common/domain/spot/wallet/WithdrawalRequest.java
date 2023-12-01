package com.bitget.openapi.common.domain.spot.wallet;

import lombok.Builder;

@Builder
public record WithdrawalRequest(
        String coin,
        TransferType transferType,
        String address,
        String chain,
        InnerToType innerToType,
        String areaCode,
        String tag,
        String size,
        String note,
        String clientOid
) {
}
