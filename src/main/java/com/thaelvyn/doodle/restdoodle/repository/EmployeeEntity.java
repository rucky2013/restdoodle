package com.thaelvyn.doodle.restdoodle.repository;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Julien Frisquet
 */
@Embeddable
public class EmployeeEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    public EmployeeEntity() {
    }

    public EmployeeEntity(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
