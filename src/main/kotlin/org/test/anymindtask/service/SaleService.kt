package org.test.anymindtask.service

import org.springframework.stereotype.Service
import org.test.anymindtask.model.Sale
import org.test.anymindtask.repository.SaleRepository
import java.time.LocalDateTime

@Service
class SaleService(private val saleRepository: SaleRepository) {

    fun getSalesReport(start: LocalDateTime, end: LocalDateTime): List<Sale> {
        return saleRepository.findSalesReport(start, end)
    }
}