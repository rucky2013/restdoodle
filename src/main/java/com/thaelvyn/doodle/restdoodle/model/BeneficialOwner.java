package com.thaelvyn.doodle.restdoodle.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public class BeneficialOwner {

    private static final String STANDARD_MESSAGE = "is missing or invalid";

    private UUID beneficialOwnerId;

    @NotEmpty(message = STANDARD_MESSAGE)
    private String firstName;

    @NotEmpty(message = STANDARD_MESSAGE)
    private String lastName;

    //TODO: [JF] probably need more things like equity. But irrelevant for the exercise

    public BeneficialOwner() {
    }

    public BeneficialOwner(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getBeneficialOwnerId() {
        return beneficialOwnerId;
    }

    public void setBeneficialOwnerId(final UUID beneficialOwnerId) {
        this.beneficialOwnerId = beneficialOwnerId;
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
                .add("beneficialOwnerId", beneficialOwnerId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
