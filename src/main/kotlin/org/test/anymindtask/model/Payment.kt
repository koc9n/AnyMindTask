package org.test.anymindtask.model

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.test.anymindtask.model.enum.PaymentMethod
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Payment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val customerId: String,
    val price: BigDecimal,
    val priceModifier: BigDecimal,
    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
    val datetime: LocalDateTime,
    @JdbcTypeCode(SqlTypes.JSON)
    val additionalItem: AdditionalItem,
    var finalPrice: BigDecimal?,
    var points: BigDecimal?
) {
    constructor(
        customerId: String,
        price: BigDecimal,
        priceModifier: BigDecimal,
        paymentMethod: PaymentMethod,
        datetime: LocalDateTime,
        additionalItem: AdditionalItem
    ) : this(
        customerId = customerId,
        price = price,
        priceModifier = priceModifier,
        paymentMethod = paymentMethod,
        datetime = datetime,
        additionalItem = additionalItem,
        finalPrice = null,
        points = null
    )
}