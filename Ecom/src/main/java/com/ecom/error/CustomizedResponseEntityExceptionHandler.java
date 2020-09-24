package com.ecom.error;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
	  @ExceptionHandler(Exception.class)
	  
	  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE) public ResponseEntity<Object>
	  handleUserNotFoundException(Exception ex,HttpHeaders headers,
	  WebRequest request) { Map<String, Object> body = new LinkedHashMap<>();
	  body.put("timestamp", new Date()); body.put("status",
	  HttpStatus.NOT_ACCEPTABLE); Map<String,String> errors=new HashMap<String,
	  String>(); errors.put("customexception", ex.getMessage());
	  body.put("errors",errors); return new ResponseEntity<>(body,headers,
	  HttpStatus.NOT_ACCEPTABLE); }
	 
  
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status, WebRequest request) {

      Map<String, Object> body = new LinkedHashMap<>();
      body.put("timestamp", new Date());
      body.put("status",HttpStatus.NOT_ACCEPTABLE);
      
      Map<String,String> errors = ex.getBindingResult()
              .getFieldErrors()
              .stream()
              .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

      body.put("errors", errors);

      return new ResponseEntity<>(body, headers, status);

  }
  
  
  @ExceptionHandler(CustomException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public final ResponseEntity<Object> handleAllExceptions(CustomException ex, WebRequest request) {
	  Map<String, Object> body = new LinkedHashMap<>();
      body.put("timestamp", new Date());
      body.put("status", HttpStatus.NOT_ACCEPTABLE);
      Map<String,String> errors=new HashMap<String, String>();
      errors.put("customexception", ex.getMessage());
      body.put("errors",errors);
     
      return new ResponseEntity<Object>(body, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
}