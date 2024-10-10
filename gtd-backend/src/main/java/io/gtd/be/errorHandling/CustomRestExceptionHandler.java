package io.gtd.be.errorHandling;

import io.gtd.be.errorHandling.exception.ServiceException;
import io.gtd.be.errorHandling.exception.TaskNotFoundException;
import io.gtd.be.errorHandling.exception.UserIdNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //

        final List<String> errors = new ArrayList<>();
        for (final var error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final var error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        var apiError = new ApiError(HttpStatus.BAD_REQUEST, "Request Validation Failed", errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.status(), request);
    }

    protected ResponseEntity<Object> handleHandlerMethodValidationException(final HandlerMethodValidationException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //

        logger.error("error", ex);
        var error = ex.getMessage();

        var apiError = new ApiError(HttpStatus.BAD_REQUEST, "Request Validation Failed", List.of(error));
        return handleExceptionInternal(ex, apiError, headers, apiError.status(), request);
    }

    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        logger.info(ex.getClass().getName());
        //
        var error = ex.getRootCause().getMessage();

        var apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), List.of(error));
        return handleExceptionInternal(ex, apiError, headers, apiError.status(), request);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    protected ResponseEntity<Object> handleTaskNotFound(TaskNotFoundException ex, WebRequest request) {
        logger.info(ex.getClass().getName());

        var error = ex.getLocalizedMessage();
        var apiError = new ApiError(HttpStatus.NOT_FOUND, error, List.of(error));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.status());
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    protected ResponseEntity<Object> handleUserIdNotFound(UserIdNotFoundException ex, WebRequest request) {
        logger.info(ex.getClass().getName());

        var error = ex.getLocalizedMessage();
        var apiError = new ApiError(HttpStatus.NOT_FOUND, error, List.of(error));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.status());
    }

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleServiceException(ServiceException ex, WebRequest request) {
        logger.info(ex.getClass().getName());

        var error = ex.getLocalizedMessage();
        var apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Server error occurred", List.of(error));
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.status());
    }
}
