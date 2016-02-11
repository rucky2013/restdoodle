package com.thaelvyn.doodle.restdoodle.dto;

import com.google.common.base.MoreObjects;
import com.thaelvyn.doodle.restdoodle.model.BaseCompany;

import java.util.List;

/**
 * @author Julien Frisquet
 */
public class CompaniesResponse extends Response {

    private List<BaseCompany> companies;

    protected CompaniesResponse() {
    }

    public CompaniesResponse(final Integer httpStatus, final String message, final List<BaseCompany> companies) {
        super(httpStatus, message);
        this.companies = companies;
    }

    public List<BaseCompany> getCompanies() {
        return companies;
    }

    public void setCompanies(final List<BaseCompany> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("companies", companies)
                .toString();
    }
}
