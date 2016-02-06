package com.thaelvyn.doodle.restdoodle.assembler;

import com.thaelvyn.doodle.restdoodle.model.BaseCompany;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.model.Employee;
import com.thaelvyn.doodle.restdoodle.repository.CompanyEntity;
import com.thaelvyn.doodle.restdoodle.repository.EmployeeEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
public class CompanyAssemblerTest {

    private CompanyAssembler companyAssembler = new CompanyAssemblerImpl();

    private List<Employee> employees;
    private List<EmployeeEntity> employeeEntities;
    private Company company;
    private CompanyEntity companyEntity;

    @Before
    public void setUp() {

        employees = new ArrayList<>();
        employees.add(new Employee("Anders", "Kjolholm"));
        employees.add(new Employee("Michael", "Poulsen"));

        employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity("Max", "Cavalera"));
        employeeEntities.add(new EmployeeEntity("Maynard James", "Keenan"));


        company = new Company("Comp Name", "1 BroadWay", "Cambridge", "UK", "info@company.com",
                "+44something", employees);

        companyEntity = new CompanyEntity(UUID.randomUUID(), "A Name", "Address stuff", "Littleport", "UK",
                "hey@stuff.org", "+44something-else", employeeEntities);
    }

    @Test
    public void testToCompanyEntityOk() {

        final CompanyEntity companyEntity = companyAssembler.toCompanyEntity(company);

        Assert.assertEquals("Company Name mismatch", company.getName(), companyEntity.getName());
        Assert.assertEquals("Address mismatch", company.getAddress(), companyEntity.getAddress());
        Assert.assertEquals("City mismatch", company.getCity(), companyEntity.getCity());
        Assert.assertEquals("Country mismatch", company.getCountry(), companyEntity.getCountry());
        Assert.assertEquals("Email mismatch", company.getEmail(), companyEntity.getEmail());
        Assert.assertEquals("Phone Number mismatch", company.getPhoneNumber(), companyEntity.getPhoneNumber());
        Assert.assertEquals("Employees first name mismatch", company.getEmployees().get(0).getFirstName(),
                companyEntity.getEmployees().get(0).getFirstName());
        Assert.assertEquals("Employees last name mismatch", company.getEmployees().get(0).getLastName(),
                companyEntity.getEmployees().get(0).getLastName());
    }

    @Test
    public void testToCompanyOk() {

        final Company company = companyAssembler.toCompany(companyEntity);

        Assert.assertEquals("Company ID mismatch", companyEntity.getCompanyId(), company.getCompanyId());
        Assert.assertEquals("Company Name mismatch", companyEntity.getName(), company.getName());
        Assert.assertEquals("Address mismatch", companyEntity.getAddress(), company.getAddress());
        Assert.assertEquals("City mismatch", companyEntity.getCity(), company.getCity());
        Assert.assertEquals("Country mismatch", companyEntity.getCountry(), company.getCountry());
        Assert.assertEquals("Email mismatch", companyEntity.getEmail(), company.getEmail());
        Assert.assertEquals("Phone Number mismatch", companyEntity.getPhoneNumber(), company.getPhoneNumber());
        Assert.assertEquals("Employees first name mismatch", companyEntity.getEmployees().get(0).getFirstName(),
                company.getEmployees().get(0).getFirstName());
        Assert.assertEquals("Employees last name mismatch", companyEntity.getEmployees().get(0).getLastName(),
                company.getEmployees().get(0).getLastName());
    }

    @Test
    public void testToBaseCompanyOk() {

        final BaseCompany baseCompany = companyAssembler.toBaseCompany(companyEntity);

        Assert.assertEquals("Company ID mismatch", companyEntity.getCompanyId(), baseCompany.getCompanyId());
        Assert.assertEquals("Company Name mismatch", companyEntity.getName(), baseCompany.getName());
    }

}
