package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@Service
public class BeneficialOwnerAssemblerImpl implements BeneficialOwnerAssembler {

    @Override
    public BeneficialOwnerEntity toBeneficialOwnerEntity(final BeneficialOwner beneficialOwner, final UUID companyId) {
        BeneficialOwnerEntity beneficialOwnerEntity = new BeneficialOwnerEntity();
        beneficialOwnerEntity.setCompanyId(companyId);
        beneficialOwnerEntity.setFirstName(beneficialOwner.getFirstName());
        beneficialOwnerEntity.setLastName(beneficialOwner.getLastName());

        return beneficialOwnerEntity;
    }

    @Override
    public BeneficialOwner toBeneficialOwner(final BeneficialOwnerEntity beneficialOwnerEntity) {
        BeneficialOwner beneficialOwner = new BeneficialOwner();
        beneficialOwner.setBeneficialOwnerId(beneficialOwnerEntity.getBeneficialOwnerId());
        beneficialOwner.setFirstName(beneficialOwnerEntity.getFirstName());
        beneficialOwner.setLastName(beneficialOwnerEntity.getLastName());
        return beneficialOwner;
    }
}
