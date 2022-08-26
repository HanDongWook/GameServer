package com.example.gameserver.entity

import com.example.gameserver.model.InventoryModel
import javax.persistence.*
import javax.validation.constraints.Positive

@Entity
@Table(name = "player_inventory")
class InventoryEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: String,

    var itemId: String,

    @field:Positive
    var count: Int = 1,
) {
    companion object {
        fun InventoryEntity.toModel() = InventoryModel(id, userId, itemId, count)
    }
}