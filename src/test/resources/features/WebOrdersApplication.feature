@ONT-115 @regression @smoke @UI
Feature: Validating WebOrders Application login functionality

  Scenario: Validating login functionality with valid credentials
    Given user navigates to the WebOrders Application
    When user provides username "Teste" and password "test" clicks on login button
    Then user validates application is logged in

  Scenario: Validating login functionality with invalid credentials
    Given user navigates to the WebOrders Application
    When user provides username "Invalid" and password "Invalid" clicks on login button
    Then user validates error message "Invalid Login or Password."


