package com.kotak.cryptocurrency.model.dto.response;

import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kotak.cryptocurrency.exception.ExceptionMappingUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    private RequestStatus status = RequestStatus.FAILED;

    private Result result;

    public ErrorResponse(final Object message, final Throwable exception) {

        log.error(exception.getMessage(), ExceptionUtils.getRootCause(exception));

        this.result = new Result(message, ExceptionMappingUtils.getErrorCode(exception));
    }

    public ErrorResponse(final Throwable exception) {
        log.error(exception.getMessage(), ExceptionUtils.getRootCause(exception));
        this.result = new Result(ExceptionMappingUtils.getMessage(exception),
                ExceptionMappingUtils.getErrorCode(exception));
    }

    @Data
    private class Result {

        Result(final Object message, final String code) {
            this.message = message;
            this.code = code;
        }

        private Object message;

        private String code;

    }

    public static enum RequestStatus {
        SUCCESS, FAILED
    }

}