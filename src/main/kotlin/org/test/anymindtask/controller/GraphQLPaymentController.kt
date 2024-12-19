package org.test.anymindtask.controller

import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Arguments
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import org.test.anymindtask.converter.PaymentConverter
import org.test.anymindtask.model.PaymentInput
import org.test.anymindtask.model.PaymentResponse
import org.test.anymindtask.service.PaymentService

@Controller
class GraphQLPaymentController(
    private val paymentService: PaymentService,
    private val paymentConverter: PaymentConverter
) {
    private val logger = LoggerFactory.getLogger(GraphQLPaymentController::class.java)

    @MutationMapping
    fun processPayment(
        @Arguments @Valid paymentInput: PaymentInput
    ): PaymentResponse {
        logger.debug("Processing payment with input: {}", paymentInput)
        val payment = paymentConverter.convertToPayment(paymentInput)
        logger.debug("Converted payment object: {}", payment)
        val savedPayment = paymentService.processPayment(payment)
        logger.debug("Saved payment object: {}", savedPayment)
        val paymentResponse = paymentConverter.convertToPaymentResponse(savedPayment)
        logger.debug("Payment response object: {}", paymentResponse)
        return paymentResponse
    }
}