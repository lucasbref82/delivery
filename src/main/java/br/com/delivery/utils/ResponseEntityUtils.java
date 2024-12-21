package br.com.delivery.utils;

import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    private ResponseEntityUtils() {

    }

    public static ResponseEntity<GenericMessage> notFoundOrInternalServerError(Throwable e) {
        if (e.getCause() instanceof NotFoundException) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(GenericMessage
                            .builder()
                            .sucess(false)
                            .message(e.getMessage())
                            .build()
                    );
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericMessage
                        .builder()
                        .sucess(false)
                        .message(e.getMessage())
                        .build()
                );
    }
}
