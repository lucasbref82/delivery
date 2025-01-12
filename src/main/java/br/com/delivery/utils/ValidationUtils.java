package br.com.delivery.utils;

import br.com.delivery.v1.domain.dto.GenericMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Set;

@Slf4j
public class ValidationUtils {

    private ValidationUtils() {

    }

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> ResponseEntity<GenericMessage> validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            var exception = new RuntimeException("Validation error");
            violations.forEach(violation ->
                    log.error("Validation error {} : {}", violation.getPropertyPath(), violation.getMessage(), exception)
            );

            return ResponseEntity.badRequest().body(
                    GenericMessage.builder()
                            .success(false)
                            .message("Validation error")
                            .result(violations.stream()
                                    .map(v -> String.format("%s : %s", v.getPropertyPath(), v.getMessage()))
                                    .toList())
                            .build()
            );
        }
        return null;
    }
}

