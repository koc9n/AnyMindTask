package org.test.anymindtask.validator.annotation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import org.test.anymindtask.validator.AdditionalItemValidator
import kotlin.reflect.KClass

@Constraint(validatedBy = [AdditionalItemValidator::class])
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidAdditionalItem(
    val message: String = "Invalid additional item",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
