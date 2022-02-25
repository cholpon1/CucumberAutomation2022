@api
Feature: Validating Books API calls
  Scenario: Validating end to end Order API calls
    Given user sends POST order API call with data
    |bookId|1|
    |customerName|Kim Yan|
    When user sends GET order API call
    Then user validates status code is 200
    When user sends PATCH order API call with data
    |customerName|John Doe|
    Then user validates status code is 204
    When user sends GET order API call
    Then user validates status code is 200
    When user sends DELETE order API call
    Then user validates status code is 204
    And user sends GET order API call
    Then user validates status code is 404