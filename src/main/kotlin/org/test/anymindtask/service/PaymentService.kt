package org.test.anymindtask.service

import org.springframework.stereotype.Service
import org.test.anymindtask.model.Payment
import org.test.anymindtask.repository.PaymentRepository
import org.test.anymindtask.strategy.PricingStrategyFactory

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {

    fun savePayment(payment: Payment): Payment {
        val strategy = PricingStrategyFactory.getStrategy(payment.paymentMethod)
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        return paymentRepository.save(payment.copy(finalPrice = finalPrice, points = points))
    }
}