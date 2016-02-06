package com.thaelvyn.doodle.restdoodle.dto;

import com.google.common.base.MoreObjects;
import com.thaelvyn.doodle.restdoodle.model.Company;

/**
 * @author Julien Frisquet
 */
public class CompanyResponse extends Response {

    private Company company;

    public CompanyResponse(final Integer httpStatus, final String message, final Company company) {
        super(httpStatus, message);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("company", company)
                .toString();
    }
}
