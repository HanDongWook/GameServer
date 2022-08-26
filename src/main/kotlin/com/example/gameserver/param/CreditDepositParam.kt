package com.example.gameserver.param

import javax.validation.constraints.PositiveOrZero

data class CreditDepositParam(
    val userId: String,

    @field:PositiveOrZero
    val paidCredit: Int,

    @field:PositiveOrZero
    val freeCredit: Int
)