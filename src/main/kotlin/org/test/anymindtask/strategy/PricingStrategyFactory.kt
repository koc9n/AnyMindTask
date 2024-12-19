package org.test.anymindtask.strategy

import org.slf4j.LoggerFactory
import org.test.anymindtask.model.enum.PaymentMethod

object PricingStrategyFactory {
    private val logger = LoggerFactory.getLogger(PricingStrategyFactory::class.java)

    fun getStrategy(paymentMethod: PaymentMethod): PricingStrategy {
        val strategy = when (paymentMethod) {
            PaymentMethod.CASH, PaymentMethod.CASH_ON_DELIVERY -> CashStrategy()
            PaymentMethod.VISA, PaymentMethod.MASTERCARD, PaymentMethod.JCB -> CardStrategy()
            PaymentMethod.AMEX -> AmexStrategy()
            PaymentMethod.LINE_PAY, PaymentMethod.PAYPAY, PaymentMethod.GRAB_PAY -> LinePayStrategy()
            PaymentMethod.POINTS, PaymentMethod.BANK_TRANSFER, PaymentMethod.CHEQUE -> NoPointsStrategy()
        }
        logger.debug("Chosen strategy for payment method {}: {}", paymentMethod, strategy::class.simpleName)
        return strategy
    }
}