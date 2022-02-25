@HrDepartmentsApi
Feature: Validating departments API calls
  Scenario: Validating department is created with API post call and shown in UI
    Given user creates department with departments POST call having random data
    Then user validates status code 201
    When user GETS created department
    Then user validates status code 200
    When user navigates to HrApp
    And user logs in with username "Mindtek" and password "MindtekStudent"
    Then user validates that created department in Departments dropdown
    When user DELETES created department
    Then user validates status code 204
    When user GETS created department
    Then user validates status code 404