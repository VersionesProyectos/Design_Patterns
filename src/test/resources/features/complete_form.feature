Feature: Complete registration form

  Background:
    Given the home page is opened
    And I navigate to the Complete Web Form page

  @smoke @regression
  Scenario Outline: Complete form submission
    When I fill the form with firstName "<firstName>" lastName "<lastName>" title "<title>" date "<date>"
    And I submit the form
    Then I should see success message "<message>"

    Examples:
      | firstName | lastName | title               | date       | message                                 |
      | Gerardo   | QA       | Automation Engineer | 03/16/2026 | The form was successfully submitted!    |
