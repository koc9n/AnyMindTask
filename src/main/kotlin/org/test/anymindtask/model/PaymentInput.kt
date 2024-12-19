package org.test.anymindtask.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.test.anymindtask.model.enum.PaymentMethod
import org.test.anymindtask.validator.annotation.ValidAdditionalItem
import org.test.anymindtask.validator.annotation.ValidEnum
import org.test.anymindtask.validator.annotation.ValidPriceModifier
import java.math.BigDecimal

@ValidPriceModifier
@ValidAdditionalItem
data class PaymentInput(
    @field:NotBlank
    val customerId: String?,
    @field:NotBlank
    val price: String?,
    @field:NotNull
    val priceModifier: BigDecimal?,
    @field:ValidEnum(enumClass = PaymentMethod::class, message = "Invalid Payment Type")
    val paymentMethod: String?,
    @field:NotBlank
    val datetime: String?,
    @field:NotNull
    val additionalItem: AdditionalItem?
)