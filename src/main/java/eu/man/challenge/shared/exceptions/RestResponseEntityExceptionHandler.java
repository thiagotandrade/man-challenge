package eu.man.challenge.shared.exceptions;

import eu.man.challenge.shared.entities.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { OrderNotFoundException.class })
    private ResponseEntity<Object> handleOrderNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetails(new Date(), ex.getMessage()),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { InvalidOrderException.class })
    private ResponseEntity<Object> handleInvalidOrderException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetails(new Date(), ex.getMessage()),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { OrderAlreadyExistsException.class })
    private ResponseEntity<Object> handleOrderAlreadyExistsException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorDetails(new Date(), ex.getMessage()),
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

