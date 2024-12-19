package org.test.anymindtask.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Sale(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val datetime: LocalDateTime,
    val sales: BigDecimal,
    val points: BigDecimal
) {
    constructor(
        datetime: LocalDateTime,
        sales: BigDecimal,
        points: BigDecimal
    ) : this(
        0L,
        datetime,
        sales,
        points
    )
}