package org.test.anymindtask.controller

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.test.anymindtask.model.Sale
import org.test.anymindtask.service.SaleService
import java.time.LocalDateTime

@Controller
class GraphQLSaleController(private val saleService: SaleService) {

    @QueryMapping
    fun getSales(@Argument startDateTime: String, @Argument endDateTime: String): List<Sale> {
        val start = LocalDateTime.parse(startDateTime)
        val end = LocalDateTime.parse(endDateTime)
        return saleService.getSalesReport(start, end)
    }
}