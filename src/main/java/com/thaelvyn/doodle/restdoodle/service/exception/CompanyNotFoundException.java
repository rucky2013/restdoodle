package com.thaelvyn.doodle.restdoodle.service.exception;

/**
 * @author Julien Frisquet
 */
public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException(final String message) {
        super(message);
    }
}
