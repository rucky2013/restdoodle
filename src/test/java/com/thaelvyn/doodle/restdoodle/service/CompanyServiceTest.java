package com.thaelvyn.doodle.restdoodle.service;

import com.thaelvyn.doodle.restdoodle.assembler.CompanyAssemblerImpl;
import com.thaelvyn.doodle.restdoodle.dto.CompaniesResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompanyResponse;
import com.thaelvyn.doodle.restdoodle.model.BaseCompany;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.model.Employee;
import com.thaelvyn.doodle.restdoodle.repository.CompanyEntity;
import com.thaelvyn.doodle.restdoodle.repository.CompanyRepository;
import com.thaelvyn.doodle.restdoodle.repository.EmployeeEntity;
import com.thaelvyn.doodle.restdoodle.service.exception.CompanyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */

@RunWith(MockitoJUnitRunner.class)
public class CompanyServiceTest {

    private static final UUID COMPANY_ID = UUID.fromString("ac6dc197-9a94-42a0-a359-c3eba4ebc");

    @Mock
    private CompanyRepository companyRepository;

    private CompanyService companyService;
    private List<EmployeeEntity> employeeEntities;
    private List<Employee> employees;
    private Company company;
    private CompanyEntity companyEntity;



    @Before
    public void setUp() {
        companyService = new CompanyServiceImpl(companyRepository, new CompanyAssemblerImpl());

        employees = new ArrayList<>();
        employees.add(new Employee("Anders", "Kjolholm"));
        employees.add(new Employee("Michael", "Poulsen"));

        employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity("Anders", "Kjolholm"));
        employeeEntities.add(new EmployeeEntity("Michael", "Poulsen"));

        company = new Company("A Name", "Address stuff", "Littleport", "UK", "hey@stuff.org", "+44something-else",
                employees);

        companyEntity = new CompanyEntity(COMPANY_ID, "A Name", "Address stuff", "Littleport", "UK",
                "hey@stuff.org", "+44something-else", employeeEntities);
    }

    @Test
    public void testGetCompaniesEmptyReturnsOk() {

        Mockito.when(companyRepository.findAll()).thenReturn(Collections.emptyList());
        final CompaniesResponse response = companyService.getCompanies();

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.OK.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.OK.getReasonPhrase(), response.getMessage());
        Assert.assertEquals("List of companies mismatch", Collections.emptyList(), response.getCompanies());
    }

    @Test
    public void testGetCompaniesNullReturnsOk() {

        Mockito.when(companyRepository.findAll()).thenReturn(null);
        final CompaniesResponse response = companyService.getCompanies();

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.OK.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.OK.getReasonPhrase(), response.getMessage());
        Assert.assertEquals("List of companies mismatch", Collections.emptyList(), response.getCompanies());
    }

    @Test
    public void testGetCompaniesReturnsOk() {

        final List<CompanyEntity> companies = new ArrayList<>();
        companies.add(companyEntity);

        final List<BaseCompany> baseCompanies = new ArrayList<>();
        final BaseCompany baseCompany = new BaseCompany("A Name");
        baseCompany.setCompanyId(COMPANY_ID);
        baseCompanies.add(baseCompany);

        Mockito.when(companyRepository.findAll()).thenReturn(companies);
        final CompaniesResponse response = companyService.getCompanies();

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.OK.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.OK.getReasonPhrase(), response.getMessage());
        Assert.assertEquals("Company ID mismatch", baseCompanies.get(0).getCompanyId(), response.getCompanies().get(0).getCompanyId());
        Assert.assertEquals("Company name mismatch", baseCompanies.get(0).getName(), response.getCompanies().get(0).getName());
    }

    @Test
    public void testCreateCompanyReturnsOk() {

        Mockito.when(companyRepository.save(Mockito.any(CompanyEntity.class))).thenReturn(companyEntity);
        final CompanyResponse response = companyService.createCompany(company);

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.CREATED.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.CREATED.getReasonPhrase(), response.getMessage());
        Assert.assertEquals("UUID not created or mismatch", COMPANY_ID, response.getCompany().getCompanyId());
    }

    @Test(expected = CompanyNotFoundException.class)
    public void testGetCompanyNotFoundThrows() {

        Mockito.when(companyRepository.findOne(Mockito.any(UUID.class))).thenReturn(null);
        companyService.getCompany(COMPANY_ID);

        Assert.fail("Should have thrown an exception if not found");
    }

    @Test
    public void testGetCompanyReturnsOk() {

        Mockito.when(companyRepository.findOne(Mockito.any(UUID.class))).thenReturn(companyEntity);
        final CompanyResponse response = companyService.getCompany(COMPANY_ID);

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.OK.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.OK.getReasonPhrase(), response.getMessage());
        Assert.assertNotNull("No company was retrieved", response.getCompany());
    }

    @Test(expected = CompanyNotFoundException.class)
    public void testUpdateCompanyNotFoundThrows() {

        Mockito.when(companyRepository.findOne(Mockito.any(UUID.class))).thenReturn(null);
        companyService.updateCompany(COMPANY_ID, company);

        Assert.fail("Should have thrown an exception if not found");
    }

    @Test
    public void testUpdateCompanyReturnsOk() {

        final CompanyEntity updatedEntity = new CompanyEntity(COMPANY_ID, "OTHER Name", "Address stuff", "Littleport", "UK",
                "hey@stuff.org", "+44something-else", employeeEntities);

        final Company updateCompany = new Company("OTHER Name", "Address stuff", "Littleport", "UK", "hey@stuff.org", "+44something-else",
                employees);

        Mockito.when(companyRepository.findOne(Mockito.any(UUID.class))).thenReturn(companyEntity);
        Mockito.when(companyRepository.save(Mockito.any(CompanyEntity.class))).thenReturn(updatedEntity);
        final CompanyResponse response = companyService.updateCompany(COMPANY_ID, company);

        Assert.assertEquals("HttpStatus mismatch", HttpStatus.OK.value(), response.getHttpStatus().longValue());
        Assert.assertEquals("Message mismatch", HttpStatus.OK.getReasonPhrase(), response.getMessage());
        Assert.assertEquals("The company name wasn't updated", updatedEntity.getName(), response.getCompany().getName());
    }
}
