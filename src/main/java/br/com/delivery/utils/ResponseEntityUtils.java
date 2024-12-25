package br.com.delivery.utils;

import br.com.delivery.v1.domain.dto.GenericMessage;
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

    public static <T> ResponseEntity<GenericMessage> conflictNotFoundOrInternalServerError(Throwable e, Class<T> t, Long id) {
        if (Objects.nonNull(t) && e.getCause() instanceof DataIntegrityViolationException) {
            log.error(Utils.format("Error when deleting resource {} of id {}, cause {}", t.getSimpleName(), id, e.getMessage()), e);
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(GenericMessage
                            .builder()
                            .message(Utils
                                    .format("Error when deleting resource {} of id {}.", t.getSimpleName(), id))
                            .build()
                    );
        }

        return notFoundOrInternalServerError(e);
    }

    public static ResponseEntity<GenericMessage> notFoundOrInternalServerError(Throwable e) {
        Throwable cause = e.getCause();
        if (e instanceof NotFoundException || (cause != null && cause instanceof NotFoundException)) {
            log.error(Utils.format("Not found {}", e.getMessage()), e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(GenericMessage.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
        return internalServerError(e);
    }


    public static ResponseEntity<GenericMessage> internalServerError(Throwable e) {
        log.error(Utils.format("Unexpected error {}", e.getMessage()), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericMessage
                        .builder()
                        .success(false)
                        .message(e.getMessage())
                        .build()
                );
    }
}
