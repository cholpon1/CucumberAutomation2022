Feature: Validating HrApp's login functionality
  @hrApp @hrAppLogin @hrapploginpositive
  Scenario Outline: Validating login functionality with valid credentials
    Given  User navigates to HR App "<Login>" Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks login button
    Then user lands on the "<Main>" page where he sees the list of the employees
    Examples:
      | Login                                      | Main                                                |
      | https://devenv-webhr-arslan.herokuapp.com/ | https://devenv-webhr-arslan.herokuapp.com/employees |
      | https://qeenv-webhr-arslan.herokuapp.com/  | https://qeenv-webhr-arslan.herokuapp.com/employees  |



  @hrApp @hrAppLogin @hrapploginnegative
  Scenario Outline: Validating login functionality with invalid credentials
    Given User navigates to HR App "<Login>" Page
    When user enters "Mindtek123" for username and "MindtekStudent123" for password and clicks login button
    Then "Invalid credentials" message is displayed
    Examples:
      | Login                                      |
      | https://devenv-webhr-arslan.herokuapp.com/ |
      | https://qeenv-webhr-arslan.herokuapp.com/  |

  @hrApp @hrAppLogout
  Scenario Outline: Validating logout functionality
    Given User navigates to HR App "<Login>" Page
    When user enters "Mindtek" for username and "MindtekStudent" for password and clicks login button
    Then user clicks on logout button and verifies "You are logged out" message is displayed
    Examples:
      | Login                                      |
      | https://devenv-webhr-arslan.herokuapp.com/ |
      | https://qeenv-webhr-arslan.herokuapp.com/  |
