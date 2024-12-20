package org.test.anymindtask.strategy

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.test.anymindtask.model.AdditionalItem
import org.test.anymindtask.model.Payment
import org.test.anymindtask.model.enum.PaymentMethod
import java.math.BigDecimal
import java.time.LocalDateTime

class PricingStrategyImplTest {

    @Test
    fun calculateFinalPriceAndPoints_CashStrategy() {
        val payment = Payment(
            customerId = "customerId",
            price = BigDecimal("10.0"),
            priceModifier = BigDecimal("0.95"),
            paymentMethod = PaymentMethod.CASH,
            datetime = LocalDateTime.now(),
            additionalItem = AdditionalItem(
                last4 = "1234",
                courier = "DHL",
                bankName = "BankName",
                accountNumber = "123456789",
                chequeNumber = "987654321"
            )
        )
        val strategy = CashStrategy()
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        assertEquals(BigDecimal("9.50").compareTo(finalPrice), 0)
        assertEquals(BigDecimal("0.5").compareTo(points), 0)
    }

    @Test
    fun calculateFinalPriceAndPoints_CardStrategy() {
        val payment = Payment(
            customerId = "customerId",
            price = BigDecimal("100.0"),
            priceModifier = BigDecimal("0.95"),
            paymentMethod = PaymentMethod.VISA,
            datetime = LocalDateTime.now(),
            additionalItem = AdditionalItem(
                last4 = "1234",
                courier = "DHL",
                bankName = "BankName",
                accountNumber = "123456789",
                chequeNumber = "987654321"
            )
        )
        val strategy = CardStrategy()
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        assertEquals(BigDecimal("95.0").compareTo(finalPrice), 0)
        assertEquals(BigDecimal("3.0").compareTo(points), 0)
    }

    @Test
    fun calculateFinalPriceAndPoints_AmexStrategy() {
        val payment = Payment(
            customerId = "customerId",
            price = BigDecimal("100.0"),
            priceModifier = BigDecimal("0.98"),
            paymentMethod = PaymentMethod.AMEX,
            datetime = LocalDateTime.now(),
            additionalItem = AdditionalItem(
                last4 = "1234",
                courier = "DHL",
                bankName = "BankName",
                accountNumber = "123456789",
                chequeNumber = "987654321"
            )
        )
        val strategy = AmexStrategy()
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        assertEquals(BigDecimal("98.0").compareTo(finalPrice), 0)
        assertEquals(BigDecimal("2.0").compareTo(points), 0)
    }

    @Test
    fun calculateFinalPriceAndPoints_LinePayStrategy() {
        val payment = Payment(
            customerId = "customerId",
            price = BigDecimal("100.0"),
            priceModifier = BigDecimal("1"),
            paymentMethod = PaymentMethod.LINE_PAY,
            datetime = LocalDateTime.now(),
            additionalItem = AdditionalItem(
                last4 = "1234",
                courier = "DHL",
                bankName = "BankName",
                accountNumber = "123456789",
                chequeNumber = "987654321"
            )
        )
        val strategy = LinePayStrategy()
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        assertEquals(BigDecimal("100.0").compareTo(finalPrice), 0)
        assertEquals(BigDecimal("1.0").compareTo(points), 0)
    }

    @Test
    fun calculateFinalPriceAndPoints_NoPointsStrategy() {
        val payment = Payment(
            id = 1L,
            customerId = "customerId",
            price = BigDecimal("100.0"),
            priceModifier = BigDecimal("0.97"),
            paymentMethod = PaymentMethod.CASH,
            datetime = LocalDateTime.now(),
            additionalItem = AdditionalItem(
                last4 = "1234",
                courier = "DHL",
                bankName = "BankName",
                accountNumber = "123456789",
                chequeNumber = "987654321"
            ),
            finalPrice = BigDecimal("95.0"),
            points = BigDecimal("10")
        )
        val strategy = NoPointsStrategy()
        val (finalPrice, points) = strategy.calculateFinalPriceAndPoints(payment)
        assertEquals(BigDecimal("97.0").compareTo(finalPrice), 0)
        assertEquals(BigDecimal.ZERO.compareTo(points), 0)
    }
}