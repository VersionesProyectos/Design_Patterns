Feature: Window navigation and alerts

  Background:
    Given the home page is opened
    And I navigate to the Switch Window page

  @regression @navigation
  Scenario: Open new tab, handle alert and return
    When I open a new tab and switch
    And I close the new tab and return
    And I handle the alert
    Then I should see the main page title equals "Welcome to Formy"
