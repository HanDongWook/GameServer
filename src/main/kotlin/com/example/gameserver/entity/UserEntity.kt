package com.example.gameserver.entity

import javax.persistence.*

@Entity
@Table(name = "user")
class UserEntity(

    @Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var userId: String,

    val name: String,

    var email: String,

    val phoneNumber: String,

    val gender: Int,
)

enum class Gender(val type: Int){
    Male(0), Female(1),
}