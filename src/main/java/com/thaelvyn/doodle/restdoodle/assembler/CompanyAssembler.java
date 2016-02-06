package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BaseCompany;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.repository.CompanyEntity;

/**
 * @author Julien Frisquet
 */
public interface CompanyAssembler {

    CompanyEntity toCompanyEntity(Company company);

    Company toCompany(CompanyEntity companyEntity);

    BaseCompany toBaseCompany(CompanyEntity companyEntity);

}
