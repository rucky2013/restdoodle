package com.thaelvyn.doodle.restdoodle.controller;

import com.thaelvyn.doodle.restdoodle.dto.Response;
import com.thaelvyn.doodle.restdoodle.service.exception.CompanyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author Julien Frisquet
 */
@ControllerAdvice
public class CommonAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CommonAdvice.class);

    @ExceptionHandler(value = CompanyNotFoundException.class)
    public ResponseEntity<Response> handleCompanyNotFound (final CompanyNotFoundException e) {
        logger.warn("Resource not found: {}", e.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Response> handleArgumentMismatch(final MethodArgumentTypeMismatchException e) {
        logger.warn("Invalid Argument: {}", e.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response> defaultExceptionHandler(final Exception e) {
        logger.warn("Invalid Argument: {}", e.getMessage());
        return new ResponseEntity<>(new Response(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.OK);
    }


}
