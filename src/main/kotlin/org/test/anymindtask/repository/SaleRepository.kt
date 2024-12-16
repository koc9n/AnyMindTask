package org.test.anymindtask.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.test.anymindtask.model.Sale
import java.time.LocalDateTime

@Repository
interface SaleRepository : JpaRepository<Sale, Long> {

    @Query(
        "SELECT new org.test.anymindtask.model.Sale(DATE_TRUNC('HOUR', p.datetime), SUM(p.finalPrice), SUM(p.points)) " +
                "FROM Payment p WHERE p.datetime BETWEEN :start AND :end GROUP BY DATE_TRUNC('hour', p.datetime)"
    )
    fun findSalesReport(@Param("start") start: LocalDateTime, @Param("end") end: LocalDateTime): List<Sale>
}