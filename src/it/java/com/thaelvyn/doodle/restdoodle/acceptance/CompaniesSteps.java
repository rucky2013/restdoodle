package com.thaelvyn.doodle.restdoodle.acceptance;

import com.thaelvyn.doodle.restdoodle.Application;
import com.thaelvyn.doodle.restdoodle.controller.CompanyController;
import com.thaelvyn.doodle.restdoodle.dto.BeneficialOwnerResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompaniesResponse;
import com.thaelvyn.doodle.restdoodle.dto.CompanyResponse;
import com.thaelvyn.doodle.restdoodle.dto.Response;
import com.thaelvyn.doodle.restdoodle.model.BeneficialOwner;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.model.Employee;
import com.thaelvyn.doodle.restdoodle.service.CompanyService;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Julien Frisquet
 */
@ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
@WebIntegrationTest
public class CompaniesSteps {

    @Autowired
    private CompanyService companyService;
    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private List<Employee> employees;
    private CompaniesResponse companiesResponse;
    private CompanyResponse companyResponse;
    private BeneficialOwner beneficialOwner;
    private HttpStatus httpStatus;
    private Company company;
    private UUID companyId;

    @Value("${server.port}")
    private String serverPort;

    private String getUrl(final String path) {
        return new StringBuilder()
                .append("http://localhost:")
                .append(serverPort)
                .append(path)
                .toString();
    }


    @Before
    public void setUp(){
        final Employee employee = new Employee("John", "Doe");
        employees = new ArrayList<>();
        employees.add(employee);
    }

    @Given("several companies created")
    public void createSeveralCompanies() {
        final Company c1 = new Company("C Name", "add 1", "Camb", "UK", "c@c.com", "+44", employees);
        final Company c2 = new Company("D Name", "add 2", "Camb2", "IRE", "d@d.com", "+353", employees);

        companyService.createCompany(c1);
        companyService.createCompany(c2);
    }

    @Given("a company created")
    public void createOneCompany() {
        company = new Company("C Name", "add 1", "Camb", "UK", "c@c.com", "+44", employees);
        companyId = companyService.createCompany(company).getCompany().getCompanyId();
    }

    @Given(".+ (.+) company (.+) request")
    public void makeCompanyRequest(final String valid, final String type) {
        if ("valid".equals(valid)) {
            company = new Company("C Name", "add 1", "Camb", "UK", "c@c.com", "+44", employees);
        } else {
            company = new Company("C Name", "add 1", "Camb", "UK", "com", "+44", employees);
        }

        if ("update".equals(type)){
            companyId = companyService.createCompany(company).getCompany().getCompanyId();
            if ("valid".equals(valid)) {
                company = new Company("UPDATED", "add 1", "Camb", "UK", "c@c.com", "+44", employees);
            } else {
                company = new Company("UPDATED", "add 1", "Camb", "UK", "invalid", "+44", employees);
            }
        }

    }

    @Given("a valid beneficial owner addition request")
    public void createBeneficialOwner() {
        beneficialOwner = new BeneficialOwner("Julz", "Luc");
    }

    @When("a request to list companies is sent")
    public void listCompanies() {
        ResponseEntity<CompaniesResponse> rc = restTemplate.getForEntity(getUrl(CompanyController.BASE_URL), CompaniesResponse.class);
        companiesResponse =  rc.getBody();
        httpStatus = rc.getStatusCode();

    }

    @When("a request to retrieve the detail of a company is sent")
    public void getCompanyDetail() {
        ResponseEntity<CompanyResponse> rc = restTemplate.getForEntity(getUrl(CompanyController.BASE_URL + "/" + companyId), CompanyResponse.class);
        companyResponse = rc.getBody();
        httpStatus = rc.getStatusCode();
    }

    @When("the (.+) request is sent")
    public void sendRequest(final String request) {
        if ("creation".equals(request)){
            ResponseEntity<CompanyResponse> rc = restTemplate.postForEntity(getUrl(CompanyController.BASE_URL), company, CompanyResponse.class);
            companyResponse = rc.getBody();
            httpStatus = rc.getStatusCode();
        } else if ("update".equals(request)){
            ResponseEntity<CompanyResponse> rc = restTemplate.exchange(getUrl(CompanyController.BASE_URL + "/" + companyId),
                    HttpMethod.PUT,
                    new HttpEntity<>(company),
                    CompanyResponse.class
                    );
            companyResponse = rc.getBody();
            httpStatus = rc.getStatusCode();
        } else {
            ResponseEntity<BeneficialOwnerResponse> rc = restTemplate.postForEntity(getUrl(CompanyController.BASE_URL + "/" + companyId + "/beneficial_owners"),
                    beneficialOwner, BeneficialOwnerResponse.class);
            httpStatus = rc.getStatusCode();
        }
    }

    @Then("a (\\d+) response is returned")
    public void responseReturned(final int status) {
        Assert.assertEquals("HttpStatus code mismatch", status, httpStatus.value());

    }

    @And("a list of companies is returned")
    public void listCompaniesReturned() {
        Assert.assertNotNull("The company list shouldn't be null", companiesResponse.getCompanies());
        Assert.assertTrue("The company list shouldn't be empty", !companiesResponse.getCompanies().isEmpty());
    }

    @And("the details of a company are returned")
    public void getCompanyReturned() {
        Assert.assertNotNull("No company was returned", companyResponse.getCompany());
        Assert.assertEquals("The company requested is not the one returned", companyId,
                companyResponse.getCompany().getCompanyId());
    }

}
