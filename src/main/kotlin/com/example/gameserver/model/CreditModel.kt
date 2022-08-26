package com.example.gameserver.model

data class CreditModel (
    val id: Long? = null,
    val userId: String,
    val paidCredit: Int,
    val freeCredit: Int
)