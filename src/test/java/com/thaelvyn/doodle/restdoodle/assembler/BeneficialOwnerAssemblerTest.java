package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public class BeneficialOwnerAssemblerTest {

    private BeneficialOwnerAssembler beneficialOwnerAssembler = new BeneficialOwnerAssemblerImpl();

    @Test
    public void testToBeneficialOwnerEntityOk() {
        final BeneficialOwner beneficialOwner = new BeneficialOwner("Michael", "Poulsen");
        final UUID companyId = UUID.randomUUID();

        final BeneficialOwnerEntity beneficialOwnerEntity = beneficialOwnerAssembler
                .toBeneficialOwnerEntity(beneficialOwner, companyId);

        Assert.assertEquals("UUID Mismatch", companyId, beneficialOwnerEntity.getCompanyId());
        Assert.assertEquals("First name mismatch", beneficialOwner.getFirstName(), beneficialOwnerEntity.getFirstName());
        Assert.assertEquals("Last name mismatch", beneficialOwner.getLastName(), beneficialOwnerEntity.getLastName());
    }

    @Test
    public void testToBeneficialOwnerOk() {
        final BeneficialOwnerEntity beneficialOwnerEntity = new BeneficialOwnerEntity(UUID.randomUUID(), "Jon", "Larsen");

        final BeneficialOwner beneficialOwner = beneficialOwnerAssembler.toBeneficialOwner(beneficialOwnerEntity);

        Assert.assertEquals("UUID Mismatch", beneficialOwnerEntity.getBeneficialOwnerId(), beneficialOwner.getBeneficialOwnerId());
        Assert.assertEquals("First name mismatch", beneficialOwnerEntity.getFirstName(), beneficialOwner.getFirstName());
        Assert.assertEquals("Last name mismatch", beneficialOwnerEntity.getLastName(), beneficialOwner.getLastName());
    }
}
