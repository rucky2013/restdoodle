package com.thaelvyn.doodle.restdoodle.dto;

import com.google.common.base.MoreObjects;

/**
 * @author Julien Frisquet
 */
public class Response {

    private Integer httpStatus;
    private String message;

    public Response(final Integer httpStatus, final String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(final Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("httpStatus", httpStatus)
                .add("message", message)
                .toString();
    }
}
