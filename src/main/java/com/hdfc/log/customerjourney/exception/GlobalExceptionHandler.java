package com.hdfc.log.customerjourney.exception;

import com.hdfc.log.customerjourney.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JourneyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleJourneyNotFound(JourneyNotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), "JOURNEY_NOT_FOUND");
    }
}
