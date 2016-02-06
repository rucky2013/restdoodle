package com.thaelvyn.doodle.restdoodle.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * @author Julien Frisquet
 *
 * A company in detail
 */
public class Company extends BaseCompany {


    @NotEmpty(message = STANDARD_MESSAGE)
    private String address;

    @NotEmpty(message = STANDARD_MESSAGE)
    private String city;

    @NotEmpty(message = STANDARD_MESSAGE)
    private String country;

    @Email(message = "is not a valid email address")
    private String email;

    private String phoneNumber;

    @NotEmpty(message = STANDARD_MESSAGE)
    private List<Employee> employees;

    public Company() {
    }

    public Company(final String name, final String address, final String city, final String country, final String email,
                   final String phoneNumber, final List<Employee> employees) {
        super.setName(name);
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employees = employees;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(final List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("address", address)
                .add("city", city)
                .add("country", country)
                .add("email", email)
                .add("phoneNumber", phoneNumber)
                .add("employees", employees)
                .toString();
    }
}
