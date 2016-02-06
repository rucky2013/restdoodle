package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerEntity;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public interface BeneficialOwnerAssembler {

    BeneficialOwnerEntity toBeneficialOwnerEntity(BeneficialOwner beneficialOwner, UUID companyId);

    BeneficialOwner toBeneficialOwner(BeneficialOwnerEntity beneficialOwnerEntity);

}
