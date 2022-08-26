package com.example.gameserver.result

import com.example.gameserver.model.CreditModel

data class CreditDepositResult(
    val totalCredit: Int
)

data class CreditListResult (
    val list: List<CreditModel>
)

data class UserCreditResult (
    val credit: CreditModel
)