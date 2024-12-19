package org.test.anymindtask.strategy

import org.test.anymindtask.model.Payment
import java.math.BigDecimal

interface PricingStrategy {
    fun calculateFinalPriceAndPoints(payment: Payment): Pair<BigDecimal, BigDecimal>
}