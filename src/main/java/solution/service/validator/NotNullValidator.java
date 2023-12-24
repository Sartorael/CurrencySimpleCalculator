package solution.service.validator;
import solution.service.validator.NotNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullValidator implements ConstraintValidator<NotNull, Double> {

    @Override
    public void initialize(NotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value != null;
    }
}