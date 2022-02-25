@regression @UI @MB2P-120
Feature: Validating calculate and order creation functionalities
  Scenario Outline: Validating calculate functionality
    Given user navigates to the WebOrders Application
    When user provides username "Tester" and password "test" clicks on login button
    And user clicks on Order module
    And user selects "<product>" product with <quantity> quantity
    Then user validates total is calculated correctly for quantity <quantity>

    Examples:
      | product     | quantity |
      | MyMoney     | 1        |
      | FamilyAlbum | 100      |
      | ScreenSaver | 55       |
      | MyMoney     | 20       |


