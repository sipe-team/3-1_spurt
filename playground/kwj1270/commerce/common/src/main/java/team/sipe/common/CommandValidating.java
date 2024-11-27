package team.sipe.common;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public interface CommandValidating<T> {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    default void validateSelf(T t) {
        final Set<ConstraintViolation<T>> violations = validator.validate(t);
        if (violations.isEmpty()) {
            return;
        }
        throw new ConstraintViolationException(violations);
    }
}