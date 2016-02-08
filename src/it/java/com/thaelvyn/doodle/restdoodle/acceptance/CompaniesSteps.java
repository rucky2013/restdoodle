package com.thaelvyn.doodle.restdoodle.acceptance;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.boot.test.WebIntegrationTest;

/**
 * @author Julien Frisquet
 */
@WebIntegrationTest
public class CompaniesSteps {

    @Given("several companies created")
    public void createSeveralCompanies() {

    }

    @Given("a company created")
    public void createOneCompany() {

    }

    @Given(".+ (.+) company (.+) request")
    public void makeCompanyRequest(final String valid, final String type) {

    }

    @Given("a valid beneficial owner addition request")
    public void createBeneficialOwner() {

    }

    @When("a request to list companies is sent")
    public void listCompanies() {

    }

    @When("a request to retrieve the detail of a company is sent")
    public void getCompanyDetail() {

    }

    @When("the (.+) request is sent")
    public void sendRequest(final String request) {

    }

    @Then("a (\\d+) response is returned")
    public void responseReturned(final int status) {

    }

    @And("a list of companies is returned")
    public void listCompaniesReturned() {

    }

    @And("the details of a company are returned")
    public void getCompanyReturned() {

    }

}
