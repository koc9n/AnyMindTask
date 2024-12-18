package org.test.anymindtask.model
import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import org.test.anymindtask.model.enum.PaymentMethod
import java.time.LocalDateTime

@Entity
data class Payment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val customerId: String,
    val price: Double,
    val priceModifier: Double,
    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod,
    val datetime: LocalDateTime,
    @JdbcTypeCode(SqlTypes.JSON)
    val additionalItem: Map<String, Any>,
    val finalPrice: Double?,
    val points: Double?
) {
    constructor(customerId: String, price: Double, priceModifier: Double, paymentMethod: PaymentMethod, datetime: LocalDateTime, additionalItem: Map<String, Any>) : this(
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