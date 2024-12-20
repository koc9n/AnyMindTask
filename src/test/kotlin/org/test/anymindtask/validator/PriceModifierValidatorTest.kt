package org.test.anymindtask.validator

import jakarta.validation.ConstraintValidatorContext
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.test.anymindtask.model.PaymentInput
import org.test.anymindtask.model.enum.PaymentMethod
import java.math.BigDecimal

class PriceModifierValidatorTest {

    private val validator = PriceModifierValidator()
    private val context = mock(ConstraintValidatorContext::class.java)

    @Test
    fun `isValid returns true for valid priceModifier with CASH`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.95"),
            paymentMethod = PaymentMethod.CASH.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertTrue(result)
    }

    @Test
    fun `isValid returns false for invalid priceModifier with CASH`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.85"),
            paymentMethod = PaymentMethod.CASH.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }

    @Test
    fun `isValid returns true for valid priceModifier with VISA`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.97"),
            paymentMethod = PaymentMethod.VISA.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertTrue(result)
    }

    @Test
    fun `isValid returns false for invalid priceModifier with VISA`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.93"),
            paymentMethod = PaymentMethod.VISA.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }

    @Test
    fun `isValid returns true for valid priceModifier with AMEX`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.99"),
            paymentMethod = PaymentMethod.AMEX.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertTrue(result)
    }

    @Test
    fun `isValid returns false for invalid priceModifier with AMEX`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("0.97"),
            paymentMethod = PaymentMethod.AMEX.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }

    @Test
    fun `isValid returns true for valid priceModifier with LINE_PAY`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("1.0"),
            paymentMethod = PaymentMethod.LINE_PAY.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertTrue(result)
    }

    @Test
    fun `isValid returns false for invalid priceModifier with LINE_PAY`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("1.01"),
            paymentMethod = PaymentMethod.LINE_PAY.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }

    @Test
    fun `isValid returns false when priceModifier is null`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = null,
            paymentMethod = PaymentMethod.CASH.name,
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }

    @Test
    fun `isValid returns false for unsupported payment method`() {
        val paymentInput = PaymentInput(
            customerId = "1",
            price = "100",
            priceModifier = BigDecimal("1.0"),
            paymentMethod = "UNSUPPORTED_METHOD",
            datetime = "2023-10-14T10:00:00",
            additionalItem = null
        )

        val result = validator.isValid(paymentInput, context)

        assertFalse(result)
    }
}