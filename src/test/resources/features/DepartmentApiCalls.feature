@api @department-api-calls
  Feature: Validating department API calls
  @department-api-calls-01
  Scenario: Department API calls validation
    Given User sends POST API call to create a department with provided data
      |departmentName|Marketing|
      |departmentId  |666      |
    Then User validates status code is 201
    When user navigates to HrApp
    And user logs in with username "Mindtek" and password "MindtekStudent"
    And Validating that department is created in UI dropdown
    When User sends GET API call to validate department is exists in department list
    When User sends GET API call to validate department is created
    Then User validates response status code is 200
    When User sends DELETE API call to delete department
    Then User validates DELETE call status code is 204
    And User validates department is not shown in UI dropdown
    When User sends GET API call to validate department is deleted
    Then User validates status code is 404



#Feature: Validating department API calls
#  Scenario: Department API calls validation
#    Given User sends POST API call to create a department with provided data
#      |departmentName| Marketing|
#    Then user validates status code 201
#    And Validating that department is created in UI dropdown
#    When User sending GET API call to validate department is created
#    Then user validates status code 200
#    When user send DELETE API call to delete department
#    Then user validates status code 204
#    And Validating department is not shown in UI dropdown
#    When User sends GET API call to validate department is deleted
#    Then user validates status code 404