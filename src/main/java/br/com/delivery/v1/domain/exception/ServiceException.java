package br.com.delivery.v1.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends Exception {
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
