package com.example.gameserver.service

import com.example.gameserver.entity.CreditEntity
import com.example.gameserver.entity.InventoryEntity
import com.example.gameserver.entity.InventoryEntity.Companion.toModel
import com.example.gameserver.param.ItemBuyParam
import com.example.gameserver.policy.credit.CreditPolicyManager
import com.example.gameserver.repository.InventoryRepository
import com.example.gameserver.repository.ItemRepository
import com.example.gameserver.result.InventoryListResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService (
    private val inventoryRepository: InventoryRepository,
    private val itemRepository: ItemRepository,
    private val creditPolicyManager: CreditPolicyManager
) {

    fun getInventoryList(): InventoryListResult {
        val list = inventoryRepository.findAll().map { it.toModel() }
        return InventoryListResult(list)
    }

    fun getItemPrice(itemId: String): Int {
//        val price = itemRepository.findByItemId(itemId)
        val price = 500
        return price
    }

    @Transactional
    fun buy(param: ItemBuyParam) {
        val price = getItemPrice(param.itemId)
        creditPolicyManager.spendCredit(param.userId, price)
        add(param.userId, param.itemId)
    }

    fun add(userId: String, itemId: String) {
        val inventoryModel = inventoryRepository.findByItemId(itemId)?.toModel()
        if (inventoryModel == null) {
            val newItem = InventoryEntity(
                userId = userId,
                itemId = itemId,
            )
            inventoryRepository.save(newItem)
        } else {
            val plusItemCount = InventoryEntity(
                id = inventoryModel.id,
                userId = inventoryModel.userId,
                itemId = inventoryModel.itemId,
                count = inventoryModel.count + 1
            )
            inventoryRepository.save(plusItemCount)
        }
    }
}