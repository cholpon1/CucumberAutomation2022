@JDBC
Feature: Validate create employee functionality
  @JDBC_TC01 @smoke
  Scenario: Validate create employee persisted in database
    Given User navigates to HR App Login Page
    When user logs in to HR App
    And creates the new employee
    Then user validates new employee is created in database