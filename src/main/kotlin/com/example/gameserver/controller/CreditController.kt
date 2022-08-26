package com.example.gameserver.controller

import com.example.gameserver.param.CreditDepositParam
import com.example.gameserver.response.CommonResponse
import com.example.gameserver.result.CreditDepositResult
import com.example.gameserver.result.CreditListResult
import com.example.gameserver.result.UserCreditResult
import com.example.gameserver.service.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/credit")
class CreditController(
    private val creditService: CreditService
) {
    @GetMapping("/list")
    fun list(): CommonResponse<CreditListResult> {
        return CommonResponse(creditService.list())
    }

    @GetMapping("/users/{userId}")
    fun getUserCredit(@PathVariable userId: String): CommonResponse<UserCreditResult> {
        return CommonResponse(creditService.getUserCredit(userId))
    }

    @PostMapping("/deposit")
    fun deposit(@RequestBody @Valid param: CreditDepositParam): CommonResponse<CreditDepositResult> {
        return CommonResponse(creditService.deposit(param))
    }
}