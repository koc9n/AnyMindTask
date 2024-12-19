import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.test.anymindtask.validator.annotation.ValidEnum

class EnumValidator : ConstraintValidator<ValidEnum, String> {
    private lateinit var enumValues: Array<out Enum<*>>

    override fun initialize(constraintAnnotation: ValidEnum) {
        enumValues = constraintAnnotation.enumClass.java.enumConstants
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true
        return enumValues.any { it.name == value }
    }
}