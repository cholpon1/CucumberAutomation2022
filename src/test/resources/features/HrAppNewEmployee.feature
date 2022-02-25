@HrAppNewEmployee
Feature: Creating a new employee in Hr App
  Scenario Outline: User creates a new employee in Hr App
    Given User navigates to HR App "<Login>" Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks login button
    And User select the first employee Steven King (ID:100) and clicks on EDIT button
    And User changes first and last names , Department name and Job title providing the details from test data and clicks on SAVE button
    And user deletes employee information with ID:101

    Then User validates that information is updated accordingly






    Examples:
      | Login                                      |

      | https://qeenv-webhr-arslan.herokuapp.com/  |



