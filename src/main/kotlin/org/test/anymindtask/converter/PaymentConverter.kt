package org.test.anymindtask.converter

import org.springframework.stereotype.Service
import org.test.anymindtask.model.Payment
import org.test.anymindtask.model.PaymentInput
import org.test.anymindtask.model.PaymentResponse
import org.test.anymindtask.model.enum.PaymentMethod
import java.time.LocalDateTime

@Service
class PaymentConverter {
    fun convertToPayment(paymentInput: PaymentInput): Payment {
        val priceValue = paymentInput.price?.toBigDecimal()
        val paymentMethodEnum = PaymentMethod.valueOf(paymentInput.paymentMethod.orEmpty())
        return Payment(
            paymentInput.customerId!!,
            priceValue!!,
            paymentInput.priceModifier!!,
            paymentMethodEnum,
            LocalDateTime.parse(paymentInput.datetime!!),
            paymentInput.additionalItem!!
        )
    }

    fun convertToPaymentResponse(payment: Payment): PaymentResponse {
        return PaymentResponse(
            customerId = payment.customerId,
            price = payment.price.toString(),
            priceModifier = payment.priceModifier,
            paymentMethod = payment.paymentMethod.toString(),
            datetime = payment.datetime.toString(),
            additionalItem = payment.additionalItem,
            finalPrice = payment.finalPrice.toString(),
            points = payment.points
        )
    }
}