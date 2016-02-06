package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.dto.BeneficialOwnerResponse;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public interface BeneficialOwnerService {

    BeneficialOwnerResponse createBeneficialOwner(BeneficialOwner beneficialOwner, UUID companyId);

}
