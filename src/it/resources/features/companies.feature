Feature: Company API
  As a user, I can create, edit or view a company, or list of companies

  Scenario: A list of companies can be returned
    Given several companies created
    When a request to list companies is sent
    Then a 200 response is returned
    And a list of companies is returned

  Scenario: The details of one company can be retrieved
    Given a company created
    When a request to retrieve the detail of a company is sent
    Then a 200 response is returned
    And the details of a company are returned

  Scenario: Attempting to retrieve non existent company details fails
    When a request to retrieve the detail of a company is sent
    Then a 400 error response is returned

  Scenario: A company can be created
    Given a valid company creation request
    When the creation request is sent
    Then a 201 response is returned

  Scenario: A company can be updated
    Given a company created
    And a valid company update request
    When the update request is sent
    Then a 200 response is returned

  Scenario: Beneficial Owners can be added to a company
    Given a valid beneficial owner addition request
    And a company created
    When the beneficial owner addition request is sent
    Then a 201 response is returned