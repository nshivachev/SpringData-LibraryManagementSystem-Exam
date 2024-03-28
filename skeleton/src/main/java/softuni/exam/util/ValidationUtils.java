package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.validation.Validator;

@Component
public class ValidationUtils {
    private final Validator validator;

    public ValidationUtils(Validator validator) {
        this.validator = validator;
    }

    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).isEmpty();
    }
}
