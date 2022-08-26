package com.example.gameserver.service

import com.example.gameserver.repository.CreditRepository
import com.example.gameserver.entity.CreditEntity
import com.example.gameserver.entity.CreditEntity.Companion.toModel
import com.example.gameserver.exception.ErrorCode
import com.example.gameserver.exception.GSException
import com.example.gameserver.param.CreditDepositParam
import com.example.gameserver.result.CreditDepositResult
import com.example.gameserver.result.CreditListResult
import com.example.gameserver.result.UserCreditResult
import org.springframework.stereotype.Service

@Service
class CreditService (
    private val creditRepository: CreditRepository,
) {
    fun list(): CreditListResult {
        val list = creditRepository.findAll().map { it.toModel() }
        return CreditListResult(list)
    }

    fun getUserCredit(userId: String): UserCreditResult {
        val creditModel = creditRepository.findByUserId(userId)?.toModel()
            ?: throw GSException(ErrorCode.NOT_EXIST_USER)

        return UserCreditResult(creditModel)
    }

    @Synchronized
    fun deposit(param: CreditDepositParam): CreditDepositResult {
        val creditModel = creditRepository.findByUserId(param.userId)?.toModel()
            ?: throw GSException(ErrorCode.NOT_EXIST_USER)

        if (param.paidCredit < 0 || param.freeCredit < 0) {
            throw GSException(ErrorCode.CAN_NOT_DEPOSIT_NEGATIVE)
        }
        val updatePaidCredit = creditModel.paidCredit + param.paidCredit
        val updateFreeCredit = creditModel.freeCredit + param.freeCredit
        val updateCredit = CreditEntity(
            id = creditModel.id,
            userId = param.userId,
            paidCredit = updatePaidCredit,
            freeCredit = updateFreeCredit
        )
        creditRepository.save(updateCredit)

        val totalCredit = updatePaidCredit + updateFreeCredit
        return CreditDepositResult(totalCredit = totalCredit)
    }
}