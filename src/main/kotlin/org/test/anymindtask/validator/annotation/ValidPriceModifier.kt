package org.test.anymindtask.validator.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.test.anymindtask.validator.PriceModifierValidator
import kotlin.reflect.KClass

@Constraint(validatedBy = [PriceModifierValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidPriceModifier(
    val message: String = "Invalid price modifier",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)