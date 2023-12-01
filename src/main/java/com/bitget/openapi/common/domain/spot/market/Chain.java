package com.bitget.openapi.common.domain.spot.market;

public record Chain(
        String chain,
        Boolean needTag,
        Boolean withdrawable,
        Boolean rechargeable,
        String withdrawFee,
        String extraWithdrawFee,
        String depositConfirm,
        String withdrawConfirm,
        String midDepositAmount,
        String minWithdrawAmount,
        String browserUrl
) {
}
