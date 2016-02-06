package com.thaelvyn.doodle.restdoodle.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * @author Julien Frisquet
 *
 * Basic information fort a company
 */
public class BaseCompany {

    protected static final String STANDARD_MESSAGE = "is missing or invalid";

    private UUID companyId;

    @NotEmpty(message = STANDARD_MESSAGE)
    private String name;

    public BaseCompany() {
    }

    public BaseCompany(final String name) {
        this.name = name;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final UUID companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("companyId", companyId)
                .add("name", name)
                .toString();
    }
}
