package com.kotak.cryptocurrency.exception;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kotak.cryptocurrency.model.dto.response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Aabid
 */

@Slf4j
@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestExceptionHandler {

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        final Map<String, String> errors = new HashMap<>();
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ErrorResponse(errors, exception);
    }

    /**
     * Handle access exception
     *
     * @param exception
     * @return
     */

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    protected ErrorResponse handleAccessDeniedException(final AccessDeniedException exception) {
        return new ErrorResponse(exception);
    }

    /**
     * Handle general exception
     *
     * @param exception
     * @return
     */

    @ExceptionHandler({Exception.class, NoSuchElementException.class})
    protected ErrorResponse handleGeneralException(final Exception exception) {
        log.error("Error while processing the request", exception);
        return new ErrorResponse(exception);
    }

    /**
     * @param exception
     * @return
     */
    @ExceptionHandler({AbstractException.class})
    protected ErrorResponse handleGeneralRuntimeException(final AbstractException exception) {
        return new ErrorResponse(exception);
    }
    
}