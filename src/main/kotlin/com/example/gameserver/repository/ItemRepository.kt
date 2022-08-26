package com.example.gameserver.repository

import com.example.gameserver.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemEntity, Long> {

    fun findByItemId(itemId: String): ItemEntity
}