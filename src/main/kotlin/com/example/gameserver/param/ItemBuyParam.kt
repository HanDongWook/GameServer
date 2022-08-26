package com.example.gameserver.param

import javax.validation.constraints.PositiveOrZero

data class ItemBuyParam(
    val userId: String,

    val itemId: String,

    @field:PositiveOrZero
    val itemCount: Int
)