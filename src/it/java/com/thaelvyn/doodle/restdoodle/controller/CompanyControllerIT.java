package com.thaelvyn.doodle.restdoodle.controller;

import com.thaelvyn.doodle.restdoodle.Application;
import com.thaelvyn.doodle.restdoodle.dto.Response;
import com.thaelvyn.doodle.restdoodle.model.Company;
import com.thaelvyn.doodle.restdoodle.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julien Frisquet
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class CompanyControllerIT {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    private List<Employee> employees;
    private Company company;


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
    public void setUp() {
        final Employee employee = new Employee("John", "Doe");
        employees = new ArrayList<>();
        employees.add(employee);

        company = new Company("C Name", "add 1", "Camb", "UK", "c@c.com", "+44", employees);
    }

    @Test
    public void checkMissingCompanyNameReturnsError() {
        company.setName(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkMissingCompanyAddressReturnsError() {
        company.setAddress(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkMissingCompanyCityReturnsError() {
        company.setCity(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkMissingCompanyCountryReturnsError() {
        company.setCountry(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
         public void checkInvalidCompanyEmailReturnsError() {
        company.setEmail("failme");
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkMissingCompanyEmployeesReturnsError() {
        company.setEmployees(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.BAD_REQUEST.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkMinimalCompanyReturnsOk() {
        company.setEmail(null);
        company.setPhoneNumber(null);
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.CREATED.value(), response.getHttpStatus().longValue());
    }

    @Test
    public void checkFullyDefinedCompanyReturnsOk() {
        final Response response = testRestTemplate.postForObject(getUrl(CompanyController.BASE_URL), company, Response.class);

        Assert.assertEquals("Http Status code mismatch", HttpStatus.CREATED.value(), response.getHttpStatus().longValue());
    }
}
