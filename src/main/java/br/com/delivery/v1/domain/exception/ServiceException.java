package br.com.delivery.v1.domain.exception;

public class ServiceException extends Exception{
    public ServiceException(String message, Throwable e) {
        super(message, e);
    }
}
