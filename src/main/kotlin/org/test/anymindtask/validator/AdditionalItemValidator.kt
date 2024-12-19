package org.test.anymindtask.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.apache.commons.lang3.EnumUtils
import org.test.anymindtask.model.AdditionalItem
import org.test.anymindtask.model.PaymentInput
import org.test.anymindtask.model.enum.Courier
import org.test.anymindtask.model.enum.PaymentMethod
import org.test.anymindtask.validator.annotation.ValidAdditionalItem
import kotlin.reflect.KProperty1


class AdditionalItemValidator : ConstraintValidator<ValidAdditionalItem, PaymentInput> {
    override fun isValid(paymentInput: PaymentInput, context: ConstraintValidatorContext): Boolean {
        val additionalItem = paymentInput.additionalItem ?: return false

        return when (paymentInput.paymentMethod) {
            PaymentMethod.CASH_ON_DELIVERY.name -> !additionalItem.courier.isNullOrEmpty() && EnumUtils.isValidEnum(Courier::class.java, additionalItem.courier)
            PaymentMethod.VISA.name, PaymentMethod.MASTERCARD.name, PaymentMethod.AMEX.name, PaymentMethod.JCB.name -> !additionalItem.last4.isNullOrEmpty() && additionalItem.last4.length == 4
            PaymentMethod.BANK_TRANSFER.name -> !additionalItem.bankName.isNullOrEmpty() && !additionalItem.accountNumber.isNullOrEmpty()
            PaymentMethod.CHEQUE.name -> !additionalItem.bankName.isNullOrEmpty() && !additionalItem.chequeNumber.isNullOrEmpty()
            else -> AdditionalItem::class.members
                .filterIsInstance<KProperty1<AdditionalItem, String?>>()
                .all { it.get(additionalItem).isNullOrEmpty() }
        }
    }
}
