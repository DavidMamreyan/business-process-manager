package ru.mamreyan.businessprocessmanager.position;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PositionNotValidAdvice {
    @ResponseBody
    @ExceptionHandler (PositionNotValidException.class)
    @ResponseStatus (HttpStatus.NOT_ACCEPTABLE)
    String positionNotValidHandler(PositionNotValidException ex) {
        return ex.getMessage();
    }
}