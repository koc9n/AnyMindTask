package org.test.anymindtask.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.test.anymindtask.model.Payment
import org.test.anymindtask.repository.PaymentRepository
import org.test.anymindtask.strategy.PricingStrategyFactory

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {
    private val logger = LoggerFactory.getLogger(PaymentService::class.java)

    fun processPayment(payment: Payment): Payment {
        val strategy = PricingStrategyFactory.getStrategy(payment.paymentMethod)
        strategy.calculateFinalPriceAndPoints(payment).let {
            payment.finalPrice = it.first
            payment.points = it.second
        }
        logger.debug("Calculated final price and points: {}", payment)
        return paymentRepository.save(payment)
    }
}