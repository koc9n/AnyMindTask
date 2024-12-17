package org.test.anymindtask.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Sale(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val datetime: LocalDateTime,
    val sales: Double,
    val points: Double
) {
    constructor(datetime: LocalDateTime, sales: Double, points: Double) : this(0L, datetime, sales, points)
}