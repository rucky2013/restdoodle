package com.thaelvyn.doodle.restdoodle.model;

import com.google.common.base.MoreObjects;

/**
 * @author Julien Frisquet
 */
public class Employee {

    private String firstName;
    private String lastName;

    // TODO: [JF] Could add more relevant parameters

    public Employee() {
    }

    public Employee(final String firstName, final String lastName) {
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
