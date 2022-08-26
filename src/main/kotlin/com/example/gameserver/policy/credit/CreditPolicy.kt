package com.example.gameserver.policy.credit

import com.example.gameserver.entity.CreditEntity
import com.example.gameserver.entity.CreditEntity.Companion.toModel
import com.example.gameserver.exception.ErrorCode
import com.example.gameserver.exception.GSException
import com.example.gameserver.repository.CreditRepository
import org.springframework.stereotype.Component

interface CreditPolicy {
    fun getPolicyName(): CreditPolicyNames
    fun execute(userId: String, price: Int)
}

@Component
class FixedDefaultPolicy(
    private val creditRepository: CreditRepository
) : CreditPolicy {
    override fun getPolicyName(): CreditPolicyNames {
        return CreditPolicyNames.FixedDefaultPolicy
    }

    override fun execute(userId: String, price: Int) {
        val creditModel = creditRepository.findByUserId(userId)?.toModel()
            ?: throw GSException(ErrorCode.NOT_EXIST_USER)

        val balance = creditModel.freeCredit + creditModel.paidCredit

        if (balance < price) {
            throw GSException(ErrorCode.NOT_ENOUGH_CREDIT)
        } else {
            if (creditModel.freeCredit < price) {
                val newCredit = CreditEntity(
                    id = creditModel.id,
                    userId = userId,
                    freeCredit = 0,
                    paidCredit = creditModel.paidCredit - (price - creditModel.freeCredit)
                )
                creditRepository.save(newCredit)
            } else {
                val newCredit = CreditEntity(
                    id = creditModel.id,
                    userId = userId,
                    freeCredit = creditModel.freeCredit - price,
                    paidCredit = creditModel.paidCredit
                )
                creditRepository.save(newCredit)
            }
        }
    }
}

@Component
class OnlyPaidCreditPolicy(
    private val creditRepository: CreditRepository
) : CreditPolicy {
    override fun getPolicyName(): CreditPolicyNames {
        return CreditPolicyNames.OnlyPaidCreditPolicy
    }

    override fun execute(userId: String, price: Int) {
        val creditModel = creditRepository.findByUserId(userId)?.toModel()
            ?: throw GSException(ErrorCode.NOT_EXIST_USER)

        val paidCredit = creditModel.paidCredit

        if (paidCredit < price) {
            throw GSException(ErrorCode.NOT_ENOUGH_PAID_CREDIT)
        } else {
            val newCredit = CreditEntity(
                id = creditModel.id,
                userId = userId,
                freeCredit = creditModel.freeCredit,
                paidCredit = creditModel.paidCredit - price
            )
            creditRepository.save(newCredit)
        }
    }
}

@Component
class OnlyFreeCreditPolicy(
    private val creditRepository: CreditRepository
) : CreditPolicy {
    override fun getPolicyName(): CreditPolicyNames {
        return CreditPolicyNames.OnlyFreeCreditPolicy
    }

    override fun execute(userId: String, price: Int) {
        val creditModel = creditRepository.findByUserId(userId)?.toModel()
            ?: throw GSException(ErrorCode.NOT_EXIST_USER)

        val freeCredit = creditModel.freeCredit

        if (freeCredit < price) {
            throw GSException(ErrorCode.NOT_ENOUGH_FREE_CREDIT)
        } else {
            val newCredit = CreditEntity(
                id = creditModel.id,
                userId = userId,
                freeCredit = creditModel.freeCredit - price,
                paidCredit = creditModel.paidCredit
            )
            creditRepository.save(newCredit)
        }
    }
}

enum class CreditPolicyNames {
    FixedDefaultPolicy, OnlyPaidCreditPolicy, OnlyFreeCreditPolicy
}