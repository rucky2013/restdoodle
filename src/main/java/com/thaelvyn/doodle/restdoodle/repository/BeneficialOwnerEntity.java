package com.thaelvyn.doodle.restdoodle.repository;

import com.google.common.base.MoreObjects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@Entity
@Table(name = "beneficial_owners")
public class BeneficialOwnerEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "beneficial_owner_id", nullable = false, columnDefinition = "BINARY(16)")
    private UUID beneficialOwnerId;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID companyId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @CreatedDate
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    public BeneficialOwnerEntity() {
    }

    public BeneficialOwnerEntity(final UUID companyId, final String firstName, final String lastName) {
        this.companyId = companyId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getBeneficialOwnerId() {
        return beneficialOwnerId;
    }

    public void setBeneficialOwnerId(final UUID beneficialOwnerId) {
        this.beneficialOwnerId = beneficialOwnerId;
    }

    public UUID getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final UUID companyId) {
        this.companyId = companyId;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("beneficialOwnerId", beneficialOwnerId)
                .add("companyId", companyId)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("dateCreated", dateCreated)
                .add("dateUpdated", dateUpdated)
                .toString();
    }
}
