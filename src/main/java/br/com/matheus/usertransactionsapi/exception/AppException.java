package br.com.matheus.usertransactionsapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {
    final private HttpStatus statusCode;

    public AppException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}