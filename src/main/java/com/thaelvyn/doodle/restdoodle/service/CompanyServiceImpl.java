package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.assembler.CompanyAssembler;
import com.thaelvyn.doodle.restdoodle.dto.CompaniesResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompanyResponse;
import com.thaelvyn.doodle.restdoodle.model.BaseCompany;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.repository.CompanyEntity;
import com.thaelvyn.doodle.restdoodle.repository.CompanyRepository;
import com.thaelvyn.doodle.restdoodle.service.exception.CompanyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Julien Frisquet
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    private final static Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;
    private final CompanyAssembler companyAssembler;

    @Autowired
    public CompanyServiceImpl(final CompanyRepository companyRepository, final CompanyAssembler companyAssembler) {
        this.companyRepository = companyRepository;
        this.companyAssembler = companyAssembler;
    }

    @Override
    public CompaniesResponse getCompanies() {
        final List<CompanyEntity> companyEntities = Optional.ofNullable(companyRepository.findAll())
                .orElse(Collections.emptyList());

        if (logger.isDebugEnabled()) {
            logger.debug("Retrieved {} companies from the database: {}", companyEntities.size(), companyEntities);
        }

        final List<BaseCompany> companies = companyEntities.stream()
                .map(companyAssembler::toBaseCompany)
                .collect(Collectors.toList());

        return new CompaniesResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companies);
    }

    @Override
    public CompanyResponse createCompany(final Company company) {
        CompanyEntity companyEntity = companyAssembler.toCompanyEntity(company);
        companyEntity = companyRepository.save(companyEntity);
        final Company savedCompany = companyAssembler.toCompany(companyEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("Converted and saved {}, associated UUID is {}", company, savedCompany.getCompanyId());
        }

        return new CompanyResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), savedCompany);

    }

    @Override
    public CompanyResponse getCompany(final UUID companyId) {
        final CompanyEntity companyEntity = loadCompany(companyId);
        final Company company = companyAssembler.toCompany(companyEntity);

        return new CompanyResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), company);

    }

    @Override
    public CompanyResponse updateCompany(final UUID companyId, final Company company) {
        // Make sure there is a valid company with this ID by loading it up
        final CompanyEntity companyEntity = loadCompany(companyId);

        CompanyEntity updatedEntity = companyAssembler.toCompanyEntity(company);
        updatedEntity.setCompanyId(companyId);

        updatedEntity = companyRepository.save(updatedEntity);

        // Reconvert just in case a fluke happened when updating the DB
        final Company updatedCompany = companyAssembler.toCompany(updatedEntity);

        if (logger.isDebugEnabled()) {
            logger.debug("Updated {} to {}", companyEntity, updatedEntity);
        }

        return new CompanyResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), updatedCompany);
    }

    private CompanyEntity loadCompany(final UUID companyId) {
        return Optional.ofNullable(companyRepository.findOne(companyId))
                .orElseThrow(() -> new CompanyNotFoundException("The provided companyId is invalid: " + companyId));
    }
}
