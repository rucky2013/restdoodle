package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BaseCompany;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.model.Employee;
import com.thaelvyn.doodle.restdoodle.repository.CompanyEntity;
import com.thaelvyn.doodle.restdoodle.repository.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Julien Frisquet
 */
@Service
public class CompanyAssemblerImpl implements CompanyAssembler {


    @Override
    public CompanyEntity toCompanyEntity(final Company company) {
        final CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(company.getName());
        companyEntity.setAddress(company.getAddress());
        companyEntity.setCity(company.getCity());
        companyEntity.setCountry(company.getCountry());
        companyEntity.setEmail(company.getEmail());
        companyEntity.setPhoneNumber(company.getPhoneNumber());
        companyEntity.setEmployees(toEmployeeEntity(company.getEmployees()));

        return companyEntity;
    }

    @Override
    public Company toCompany(final CompanyEntity companyEntity) {
        final Company company = new Company();
        company.setCompanyId(companyEntity.getCompanyId());
        company.setName(companyEntity.getName());
        company.setAddress(companyEntity.getAddress());
        company.setCity(companyEntity.getCity());
        company.setCountry(companyEntity.getCountry());
        company.setEmail(companyEntity.getEmail());
        company.setPhoneNumber(companyEntity.getPhoneNumber());
        company.setEmployees(toEmployee(companyEntity.getEmployees()));

        return company;
    }

    @Override
    public BaseCompany toBaseCompany(final CompanyEntity companyEntity) {
        final BaseCompany baseCompany = new BaseCompany();
        baseCompany.setCompanyId(companyEntity.getCompanyId());
        baseCompany.setName(companyEntity.getName());
        return baseCompany;
    }

    private List<EmployeeEntity> toEmployeeEntity(final List<Employee> employees) {
        return employees.stream()
                .map(e -> new EmployeeEntity(e.getFirstName(), e.getLastName()))
                .collect(Collectors.toList());
    }

    private List<Employee> toEmployee(final List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream()
                .map(e -> new Employee(e.getFirstName(), e.getLastName()))
                .collect(Collectors.toList());
    }

}
