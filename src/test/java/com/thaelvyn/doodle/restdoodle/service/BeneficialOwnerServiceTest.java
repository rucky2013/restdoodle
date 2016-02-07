package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.assembler.BeneficialOwnerAssemblerImpl;
import com.thaelvyn.doodle.restdoodle.dto.BeneficialOwnerResponse;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerEntity;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@RunWith(MockitoJUnitRunner.class)
public class BeneficialOwnerServiceTest {

    private static final UUID COMPANY_ID = UUID.randomUUID();
    private static final UUID BENEFICIAL_OWNER_ID = UUID.randomUUID();

    @Mock
    private BeneficialOwnerRepository repository;
    @Mock
    private CompanyService companyService;

    private BeneficialOwnerService service;
    private BeneficialOwner beneficialOwner;
    private BeneficialOwnerEntity beneficialOwnerEntity;

    @Before
    public void setUp() {
        service = new BeneficialOwnerServiceImpl(repository, companyService, new BeneficialOwnerAssemblerImpl());
        beneficialOwner = new BeneficialOwner("Bob", "Doe");
        beneficialOwnerEntity = new BeneficialOwnerEntity(COMPANY_ID, "Bob", "Doe");
        beneficialOwnerEntity.setBeneficialOwnerId(BENEFICIAL_OWNER_ID);
    }

    @Test
    public void testCreateBeneficialOwnerReturnsOk() {
        Mockito.when(repository.save(Mockito.any(BeneficialOwnerEntity.class))).thenReturn(beneficialOwnerEntity);

        final BeneficialOwnerResponse response = service.createBeneficialOwner(beneficialOwner, COMPANY_ID);

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.CREATED.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.CREATED.getReasonPhrase(), response.getMessage());
        Assert.assertNotNull("The beneficial owner id was not added", response.getBeneficialOwner().getBeneficialOwnerId());
    }
}
