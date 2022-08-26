package com.example.gameserver.repository

import com.example.gameserver.entity.InventoryEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository : JpaRepository<InventoryEntity, Long> {

    fun findByItemId(itemId: String): InventoryEntity?
}