package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.assembler.BeneficialOwnerAssembler;
import com.thaelvyn.doodle.restdoodle.dto.BeneficialOwnerResponse;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerEntity;
import com.thaelvyn.doodle.restdoodle.repository.BeneficialOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@Service
public class BeneficialOwnerServiceImpl implements BeneficialOwnerService {

    private final BeneficialOwnerRepository repository;
    private final CompanyService companyService;
    private final BeneficialOwnerAssembler assembler;

    @Autowired
    public BeneficialOwnerServiceImpl(final BeneficialOwnerRepository repository, final CompanyService companyService,
                                      final BeneficialOwnerAssembler assembler) {
        this.repository = repository;
        this.companyService = companyService;
        this.assembler = assembler;
    }

    @Override
    public BeneficialOwnerResponse createBeneficialOwner(final BeneficialOwner beneficialOwner, final UUID companyId) {
        // Load the company to make sure it exists. Don't really care about the result as long as it doesn't throw
        companyService.getCompany(companyId);

        BeneficialOwnerEntity beneficialOwnerEntity = assembler.toBeneficialOwnerEntity(beneficialOwner, companyId);
        beneficialOwnerEntity = repository.save(beneficialOwnerEntity);

        final BeneficialOwner savedBeneficialOwner = assembler.toBeneficialOwner(beneficialOwnerEntity);

        return new BeneficialOwnerResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                savedBeneficialOwner);

    }
}
