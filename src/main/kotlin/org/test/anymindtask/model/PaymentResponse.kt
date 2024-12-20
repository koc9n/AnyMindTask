package org.test.anymindtask.model

import java.math.BigDecimal

data class PaymentResponse(
    val customerId: String? = null,
    val price: String? = null,
    val priceModifier: BigDecimal? = null,
    val paymentMethod: String? = null,
    val datetime: String? = null,
    val additionalItem: AdditionalItem? = null,
    val finalPrice: String? = null,
    val points: BigDecimal? = null,
)