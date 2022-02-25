@HrAppProject
Feature: Validating employee with database employee data
  Scenario Outline: Validating employee with database employee data
    Given user navigates to HrApp
    When user logs in with username "Mindtek" and password "MindtekStudent"
    And user search for employee with employee id "<employeeId>"
    Then user validates that "<employeeId>" data in UI matches with Database data

    Examples:
    |employeeId|
    |100       |
    |200       |
    |206       |

