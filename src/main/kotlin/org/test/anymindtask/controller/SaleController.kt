package org.test.anymindtask.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.test.anymindtask.model.Sale
import org.test.anymindtask.service.SaleService
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/sales")
class SaleController(private val saleService: SaleService) {

    @GetMapping
    fun getSalesReport(@RequestParam start: LocalDateTime, @RequestParam end: LocalDateTime): ResponseEntity<List<Sale>> {
        val salesReport = saleService.getSalesReport(start, end)
        return ResponseEntity.ok(salesReport)
    }
}