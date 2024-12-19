package org.test.anymindtask.strategy

import org.test.anymindtask.model.Payment
import java.math.BigDecimal

class CashStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal> {
        val finalPrice = payment.price.multiply(payment.priceModifier)
        val points = payment.price.multiply(BigDecimal("0.05"))
        return Pair(finalPrice, points)
    }
}

class CardStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal> {
        val finalPrice = payment.price.multiply(payment.priceModifier)
        val points = payment.price.multiply(BigDecimal("0.03"))
        return Pair(finalPrice, points)
    }
}

class AmexStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal> {
        val finalPrice = payment.price.multiply(payment.priceModifier)
        val points = payment.price.multiply(BigDecimal("0.02"))
        return Pair(finalPrice, points)
    }
}

class LinePayStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal> {
        val finalPrice = payment.price.multiply(payment.priceModifier)
        val points = payment.price.multiply(BigDecimal("0.01"))
        return Pair(finalPrice, points)
    }
}

class NoPointsStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal> {
        val finalPrice = payment.price.multiply(payment.priceModifier)
        return Pair(finalPrice, BigDecimal.ZERO)
    }
}