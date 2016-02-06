package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.dto.CompaniesResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompanyResponse;
import com.thaelvyn.doodle.restdoodle.model.Company;

import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public interface CompanyService {

    CompaniesResponse getCompanies();

    CompanyResponse createCompany(Company company);

    CompanyResponse getCompany(UUID companyId);

    CompanyResponse updateCompany(UUID companyId, Company company);

}
