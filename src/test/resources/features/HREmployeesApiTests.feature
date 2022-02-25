@HrEmployeesApi
Feature: Validating employees API calls

  Scenario Outline: Validating DB, UI, and API data matching for employees
    Given user gets employee from database with employeeId <employeeId>
    When user navigates to HrApp
    And user logs in with username "Mindtek" and password "MindtekStudent"
    And user search for employee with employee id <employeeId>
    Then user validates that <employeeId> data in UI matches with Database data
    When user GETS employee with <employeeId>
    Then user validates status code 200
    And user validates API data matched with DB data

    Examples:
      | employeeId |
      | 120        |
      | 123        |
      | 206        |

    @MB3-55
    Scenario: Creating employee with post api call
      Given user create employee with post api call with data
      |firstName|Steven|
      |lastName |King|
      |departmentName|Executive|
      Then user validates status code 201
      When user navigates to HrApp
      And user logs in with username "Mindtek" and password "MindtekStudent"
      And user searches for created employee
      Then user validates employee is created in UI with data
        |firstName|Steven|
        |lastName |King|
        |departmentName|Executive|

      @MB3-56
      Scenario: Validating number of employees in specific department
        Given user gets number of employees in "IT" department from DB
        When user navigates to HrApp
        And user logs in with username "Mindtek" and password "MindtekStudent"
        And user selects "IT" department from dropdown
        Then user validates UI number of employees matches with DB number
        When user gets  employees from "IT" department with api call
        Then user validates number of employees in API matches with DB number



