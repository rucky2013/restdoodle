package com.thaelvyn.doodle.restdoodle.repository;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@Entity
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "company_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID companyId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    private String email;

    private String phoneNumber;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name="company_id_key"))
    private List<EmployeeEntity> employees;

    @CreatedDate
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    public CompanyEntity() {
    }

    public CompanyEntity(final UUID companyId, final String name, final String address, final String city,
                         final String country, final String email, final String phoneNumber,
                         final List<EmployeeEntity> employees) {
        this.companyId = companyId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.employees = employees;
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

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(final List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("companyId", companyId)
                .add("name", name)
                .add("address", address)
                .add("city", city)
                .add("country", country)
                .add("email", email)
                .add("phoneNumber", phoneNumber)
                .add("employees", employees)
                .add("dateCreated", dateCreated)
                .add("dateUpdated", dateUpdated)
                .toString();
    }
}
