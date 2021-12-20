package com.danny.dictionary;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class WordDoesNotExistAdvice {

    @ResponseBody
    @ExceptionHandler(WordDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String WordDoesNotExistHandler(WordDoesNotExistException e) {
        return e.getMessage();
    }
}
