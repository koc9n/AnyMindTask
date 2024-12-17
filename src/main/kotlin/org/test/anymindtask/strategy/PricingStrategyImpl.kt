package org.test.anymindtask.strategy

import org.test.anymindtask.model.Payment

class CashStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double> {
        val finalPrice = payment.price * payment.priceModifier
        val points = payment.price * 0.05
        return Pair(finalPrice, points)
    }
}

class CardStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double> {
        val finalPrice = payment.price * payment.priceModifier
        val points = payment.price * 0.03
        return Pair(finalPrice, points)
    }
}

class AmexStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double> {
        val finalPrice = payment.price * payment.priceModifier
        val points = payment.price * 0.02
        return Pair(finalPrice, points)
    }
}

class LinePayStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double> {
        val finalPrice = payment.price * payment.priceModifier
        val points = payment.price * 0.01
        return Pair(finalPrice, points)
    }
}

class NoPointsStrategy : PricingStrategy {
    override fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double> {
        val finalPrice = payment.price * payment.priceModifier
        return Pair(finalPrice, 0.0)
    }
}