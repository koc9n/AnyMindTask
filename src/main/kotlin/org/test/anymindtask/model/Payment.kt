package org.test.anymindtask.model

import jakarta.persistence.*
import org.test.anymindtask.model.enum.PaymentMethod

@Entity
data class Payment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val customerId: String,
    val price: Double,
    val priceModifier: Double,
    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
    val datetime: String,
    val additionalItem: String,
    val finalPrice: Double,
    val points: Double
)