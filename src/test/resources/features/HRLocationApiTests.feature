@api @hrApi
Feature: Validating locations API calls
  Scenario Outline: Validating get location call
    Given user sends GET location API call with locationId <locationId>
    Then user validates 200 status code
    And user validates response body with data
    |locationCountry|<locationCountry>|
    |locationCity   |<locationCity>|

    Examples:
    |locationId|locationCountry|locationCity|
    |1000      |IT             |Roma        |
    |1200      |JP             |Tokyo       |
    |1400      |US             |Southlake   |


    Scenario: Validating location API calls
      Given user sends POST location API call with data
        | locationCity    | Chicago  |
        | locationCountry | US       |
        | locationState   | Illinois |
  #      | locationId      | generate |
      Then user validates 201 status code
      When user sends GET location API call with created locationId
      Then user validates 200 status code
      When user sends DELETE location API call with created locationId
      And user validates 204 status code
      When user sends GET location API call with created locationId
      Then user validates 404 status code

