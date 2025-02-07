package br.com.delivery.v1.exception;


import br.com.delivery.v1.model.dto.MensagemRetorno;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<MensagemRetorno> handleNaoEncontradoException(NaoEncontradoException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(false)
                        .resultado(e.getMessage())
                        .build()
                );
    }


    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<MensagemRetorno> handleEntidadeEmUsoException(EntidadeEmUsoException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(false)
                        .resultado(e.getMessage())
                        .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemRetorno> handleException(Exception e) {
        log.error("Ocorreu um erro inesperado: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MensagemRetorno
                        .builder()
                        .sucesso(false)
                        .resultado("Ocorreu um erro inesperado: " + e.getMessage()).build()
                );
    }

}
