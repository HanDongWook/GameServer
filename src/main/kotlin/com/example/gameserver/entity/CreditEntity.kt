package com.example.gameserver.entity

import com.example.gameserver.model.CreditModel
import javax.persistence.*
import javax.validation.constraints.PositiveOrZero

@Entity
@Table(name = "player_credit")
class CreditEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val userId: String,

    @field:PositiveOrZero
    var paidCredit: Int = 0,

    @field:PositiveOrZero
    var freeCredit: Int = 0,

) {
    companion object {
        fun CreditEntity.toModel() = CreditModel(id, userId, paidCredit, freeCredit)
    }
}