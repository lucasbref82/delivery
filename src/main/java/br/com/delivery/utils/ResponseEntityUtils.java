package br.com.delivery.utils;

import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.exception.BusinessException;
import br.com.delivery.v1.domain.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Slf4j
public class ResponseEntityUtils {

    private ResponseEntityUtils() {

    }

    public static <T> ResponseEntity<GenericMessage> genericMessageResponseEntity(Throwable e, Class<T> t, Long id) {
        if (Objects.nonNull(t) && ((e instanceof DataIntegrityViolationException) || (e.getCause() instanceof DataIntegrityViolationException))) {
            return getGenericMessageResponseEntity(e, Utils.format("Error when deleting resource {} of id {}.", t.getSimpleName(), id), HttpStatus.CONFLICT);
        }

        return genericMessageResponseEntity(e);
    }

    public static ResponseEntity<GenericMessage> genericMessageResponseEntity(Throwable e) {
        if (e instanceof BusinessException || e.getCause() instanceof BusinessException) {
            return getGenericMessageResponseEntity(e, Utils.format("Business exception: {}", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        if (e instanceof NotFoundException || e.getCause() instanceof NotFoundException) {
            return getGenericMessageResponseEntity(e, Utils.format("Not found exception: {}", e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return getGenericMessageResponseEntity(e, Utils.format("Unexpected error {}", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ResponseEntity<GenericMessage> getGenericMessageResponseEntity(Throwable e, String formattedMessage, HttpStatus httpStatus) {
        log.error(formattedMessage, e);
        return ResponseEntity
                .status(httpStatus)
                .body(GenericMessage.builder()
                        .success(false)
                        .message(e.getCause().getMessage())
                        .build());
    }
}
