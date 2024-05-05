package com.dbs.account.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)

  public ResponseEntity <ErrorHandler> accountNotFoundException(AccountNotFoundException ex) {

	  ErrorHandler errorModel = new ErrorHandler(0, ex.getMessage());

    return new ResponseEntity <ErrorHandler> (errorModel, HttpStatus.NOT_FOUND);

  }

  @ExceptionHandler(Exception.class)

  public ResponseEntity < ? > globleExcpetionHandler(Exception ex) {

    ErrorHandler errorModel = new ErrorHandler(0, ex.getMessage());

    return new ResponseEntity < > (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

  }
  
  @ExceptionHandler(InvalidAccountTypeException.class)

  public ResponseEntity <ErrorHandler> invalidAccountTypeException(InvalidAccountTypeException ex) {

	  ErrorHandler errorModel = new ErrorHandler(0, ex.getMessage());

    return new ResponseEntity <ErrorHandler> (errorModel, HttpStatus.BAD_REQUEST);

  }
}
