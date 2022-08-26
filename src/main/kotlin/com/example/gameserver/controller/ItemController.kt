package com.example.gameserver.controller

import com.example.gameserver.param.ItemBuyParam
import com.example.gameserver.response.CommonResponse
import com.example.gameserver.result.InventoryListResult
import com.example.gameserver.service.ItemService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/item")
class ItemController(
    private val itemService: ItemService
) {

    @GetMapping("/list")
    fun list(): CommonResponse<InventoryListResult> {
        return CommonResponse(itemService.getInventoryList())
    }

    @PostMapping("/buy")
    fun buy(@RequestBody @Valid param: ItemBuyParam): CommonResponse<Unit> {
        return CommonResponse(itemService.buy(param))
    }
}