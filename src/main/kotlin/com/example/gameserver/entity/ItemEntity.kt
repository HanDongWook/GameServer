package com.example.gameserver.entity

import com.example.gameserver.model.ItemModel
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "item")
class ItemEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @NotBlank
    val itemId: String,

    @field:PositiveOrZero
    var price: Int
) {
    companion object {
        fun ItemEntity.toModel() = ItemModel(id, itemId, price)
    }
}