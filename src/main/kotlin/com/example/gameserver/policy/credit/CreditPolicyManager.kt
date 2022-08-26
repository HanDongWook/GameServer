package com.example.gameserver.policy.credit

import org.springframework.beans.factory.InitializingBean
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component


@Component
class CreditPolicyManager(
    private val applicationContext: ApplicationContext
) : InitializingBean {

    private val policyMap = hashMapOf<CreditPolicyNames, CreditPolicy>()

    @Synchronized
    fun spendCredit(userId: String, price: Int) {
        policyMap[CreditPolicyNames.FixedDefaultPolicy]?.execute(userId, price)
    }

    override fun afterPropertiesSet() {
        val policyList: Collection<CreditPolicy> = applicationContext.getBeansOfType(CreditPolicy::class.java).values

        for (policy in policyList) {
            policyMap[policy.getPolicyName()] = policy
        }
    }
}