package com.example.gameserver.model

data class InventoryModel (
    val id: Long? = null,
    val userId: String,
    var itemId: String,
    var count: Int = 1,
)


