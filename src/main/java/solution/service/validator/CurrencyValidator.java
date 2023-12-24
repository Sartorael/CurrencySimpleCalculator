package solution.service.validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private final List<String> allowedCurrencies = Arrays.asList("USD", "RUR", "EUR"); // список допустимых валют

    @Override
    public void initialize(Currency constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && allowedCurrencies.contains(value.toUpperCase());
    }
}