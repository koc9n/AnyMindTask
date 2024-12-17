package org.test.anymindtask.strategy

import org.test.anymindtask.model.Payment

interface PricingStrategy {
    fun calculateFinalPriceAndPoints(payment: Payment): Pair<Double, Double>
}