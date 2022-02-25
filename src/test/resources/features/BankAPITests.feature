@api @MB3-134
Feature: Validating Bank customers api calls
  @MB3-134
  Scenario: Validating limit query parameter for customers api call
    Given user gets all customers with api call with LIMIT 3
    Then user validates bank api status code is 200
    And user validates that response includes 3 customers only


