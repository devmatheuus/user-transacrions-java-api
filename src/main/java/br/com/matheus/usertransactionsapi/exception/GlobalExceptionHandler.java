package br.com.matheus.usertransactionsapi.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final @NotNull HttpHeaders headers, final @NotNull HttpStatusCode status, final @NotNull WebRequest request) {
        final HashMap<String, HashMap<String, String>> returnedObject = new HashMap<>();
        final HashMap<String, String> allErrorsObject = new HashMap<>();

        final List<ObjectError> allErrorsList = ex.getAllErrors();

        allErrorsList.forEach(error -> {
            String message = error.getDefaultMessage();
            String field;

            if (error instanceof FieldError) {
                field = ((FieldError) error).getField();
            } else {
                field = error.getObjectName();
            }

            allErrorsObject.put(field, message);
        });

        returnedObject.put("message", allErrorsObject);

        return new ResponseEntity<>(returnedObject, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(final @NotNull AppException ex) {
        final HashMap<String, String> returnedObject = new HashMap<>();

        returnedObject.put("message", ex.getMessage());

        return new ResponseEntity<>(returnedObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal() {
        final HashMap<String, String> returnedObject = new HashMap<>();

        returnedObject.put("message", "Internal server error");

        return new ResponseEntity<>(returnedObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}