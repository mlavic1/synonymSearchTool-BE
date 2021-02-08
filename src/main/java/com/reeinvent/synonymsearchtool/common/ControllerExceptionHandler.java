package com.reeinvent.synonymsearchtool.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleInvalidRequest(HttpMessageNotReadableException messageException) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Some of input fields are not valid. %s", messageException.getMessage()));
  }
}
