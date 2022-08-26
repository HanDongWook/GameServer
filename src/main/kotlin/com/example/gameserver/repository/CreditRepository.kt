package com.example.gameserver.repository

import com.example.gameserver.entity.CreditEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository : JpaRepository<CreditEntity, Long> {

    fun findByUserId(userId: String): CreditEntity?
}