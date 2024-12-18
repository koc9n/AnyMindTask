package org.test.anymindtask.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller
import org.test.anymindtask.model.Payment
import org.test.anymindtask.model.enum.PaymentMethod
import org.test.anymindtask.service.PaymentService
import java.time.LocalDateTime

@Controller
class GraphQLPaymentController(private val paymentService: PaymentService) {

    @MutationMapping
    fun processPayment(
        @Argument customerId: String,
        @Argument price: String,
        @Argument priceModifier: Double,
        @Argument paymentMethod: String,
        @Argument datetime: String,
        @Argument additionalItem: String
    ): Payment {
        val payment = Payment(
            customerId = customerId,
            price = price.toDouble(),
            priceModifier = priceModifier,
            paymentMethod = PaymentMethod.valueOf(paymentMethod),
            datetime = LocalDateTime.parse(datetime),
            additionalItem = jacksonObjectMapper().readValue(additionalItem, Map::class.java) as Map<String, String>
        )
        return paymentService.savePayment(payment)
    }
}