package ru.mamreyan.businessprocessmanager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ObjectNotValidAdvice {
    @ResponseBody
    @ExceptionHandler (ObjectNotValidException.class)
    @ResponseStatus (HttpStatus.NOT_ACCEPTABLE)
    String objectNotValidHandler(ObjectNotValidException ex) {
        return ex.getMessage();
    }
}