package org.test.anymindtask.model

import org.test.anymindtask.model.enum.PaymentMethod
import java.math.BigDecimal

data class PaymentResponse(
    val customerId: String? = null,
    val price: String? = null,
    val priceModifier: BigDecimal? = null,
    val paymentMethod: PaymentMethod? = null,
    val datetime: String? = null,
    val additionalItem: AdditionalItem? = null,
    val finalPrice: String? = null,
    val points: BigDecimal? = null,
)