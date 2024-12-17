package org.test.anymindtask.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.test.anymindtask.model.Payment
import org.test.anymindtask.service.PaymentService

@RestController
@RequestMapping("/api/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping
    fun createPayment(@RequestBody payment: Payment): ResponseEntity<Any> {
        return try {
            val savedPayment = paymentService.savePayment(payment)
            ResponseEntity.ok(savedPayment)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(mapOf("error" to e.message))
        }
    }
}