package org.test.anymindtask.strategy

import org.test.anymindtask.model.enum.PaymentMethod

object PricingStrategyFactory {
    fun getStrategy(paymentMethod: PaymentMethod): PricingStrategy {
        return when (paymentMethod) {
            PaymentMethod.CASH, PaymentMethod.CASH_ON_DELIVERY -> CashStrategy()
            PaymentMethod.VISA, PaymentMethod.MASTERCARD, PaymentMethod.JCB -> CardStrategy()
            PaymentMethod.AMEX -> AmexStrategy()
            PaymentMethod.LINE_PAY, PaymentMethod.PAYPAY, PaymentMethod.GRAB_PAY -> LinePayStrategy()
            PaymentMethod.POINTS, PaymentMethod.BANK_TRANSFER, PaymentMethod.CHEQUE -> NoPointsStrategy()
        }
    }
}