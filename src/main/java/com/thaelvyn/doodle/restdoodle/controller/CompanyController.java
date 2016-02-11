package com.thaelvyn.doodle.restdoodle.controller;

import com.thaelvyn.doodle.restdoodle.dto.BeneficialOwnerResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompaniesResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompanyResponse;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.service.BeneficialOwnerService;
import com.thaelvyn.doodle.restdoodle.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@RestController
@RequestMapping(value= CompanyController.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    public static final String BASE_URL = "/v1/companies";

    private final CompanyService companyService;
    private final BeneficialOwnerService beneficialOwnerService;

    @Autowired
    public CompanyController(final CompanyService companyService, final BeneficialOwnerService beneficialOwnerService) {
        this.companyService = companyService;
        this.beneficialOwnerService = beneficialOwnerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CompaniesResponse getAllCompanies() {

        return companyService.getCompanies();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@Valid @RequestBody final Company company) {

        return companyService.createCompany(company);
    }

    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse getCompany(@PathVariable final UUID companyId) {

        return companyService.getCompany(companyId);
    }

    @RequestMapping(value = "/{companyId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public CompanyResponse updateCompany(@PathVariable final UUID companyId, @Valid @RequestBody final Company company) {

        return companyService.updateCompany(companyId, company);
    }

    @RequestMapping(value = "/{companyId}/beneficial_owners", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public BeneficialOwnerResponse createBeneficialOwner(@PathVariable final UUID companyId,
                                                         @Valid @RequestBody final BeneficialOwner beneficialOwner) {

        return beneficialOwnerService.createBeneficialOwner(beneficialOwner, companyId);
    }



}
