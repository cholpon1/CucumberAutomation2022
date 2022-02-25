@etsyApp
Feature: Validating Etsy application search and filter functionalities
  Background: Repeated first steps for EtsyApp
    Given user navigates to Etsy application
    When user searches for "carpet"
    And user applies price filter over 1000


@etsyApp
  Scenario: Validating search results

    Then user validates that listed product names are containing the word "carpet" and user validates that item prices are over 1000

