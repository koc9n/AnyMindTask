package org.test.anymindtask.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.test.anymindtask.model.PaymentInput
import org.test.anymindtask.model.enum.PaymentMethod
import org.test.anymindtask.validator.annotation.ValidPriceModifier
import java.math.BigDecimal

class PriceModifierValidator : ConstraintValidator<ValidPriceModifier, PaymentInput> {
    override fun isValid(paymentInput: PaymentInput, context: ConstraintValidatorContext): Boolean {
        val priceModifier = paymentInput.priceModifier ?: return false
        return when (paymentInput.paymentMethod) {
            PaymentMethod.CASH.name -> priceModifier in BigDecimal("0.9")..BigDecimal("1.0")
            PaymentMethod.CASH_ON_DELIVERY.name -> priceModifier in BigDecimal("1.0")..BigDecimal("1.02")
            PaymentMethod.VISA.name, PaymentMethod.MASTERCARD.name, PaymentMethod.JCB.name -> priceModifier in BigDecimal(
                "0.95"
            )..BigDecimal("1.0")

            PaymentMethod.AMEX.name -> priceModifier in BigDecimal("0.98")..BigDecimal("1.01")
            PaymentMethod.LINE_PAY.name, PaymentMethod.PAYPAY.name, PaymentMethod.GRAB_PAY.name -> priceModifier == BigDecimal(
                "1.0"
            )

            PaymentMethod.CHEQUE.name -> priceModifier in BigDecimal("0.9")..BigDecimal("1.0")
            else -> false
        }
    }
}