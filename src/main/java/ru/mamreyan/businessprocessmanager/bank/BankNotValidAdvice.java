package ru.mamreyan.businessprocessmanager.bank;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class BankNotValidAdvice {
    @ResponseBody
    @ExceptionHandler (BankNotValidException.class)
    @ResponseStatus (HttpStatus.NOT_ACCEPTABLE)
    String bankNotValidHandler(BankNotValidException ex) {
        return ex.getMessage();
    }
}