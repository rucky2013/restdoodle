package com.thaelvyn.doodle.restdoodle.dto;

import com.google.common.base.MoreObjects;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;

/**
 * @author Julien Frisquet
 */
public class BeneficialOwnerResponse extends Response {

    private BeneficialOwner beneficialOwner;

    public BeneficialOwnerResponse() {
    }

    public BeneficialOwnerResponse(final Integer httpStatus, final String message, final BeneficialOwner beneficialOwner) {
        super(httpStatus, message);
        this.beneficialOwner = beneficialOwner;
    }

    public BeneficialOwner getBeneficialOwner() {
        return beneficialOwner;
    }

    public void setBeneficialOwner(final BeneficialOwner beneficialOwner) {
        this.beneficialOwner = beneficialOwner;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("super", super.toString())
                .add("beneficialOwner", beneficialOwner)
                .toString();
    }
}
