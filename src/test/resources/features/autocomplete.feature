Feature: Autocomplete address
  In order to fill address forms quickly
  As a user
  I want the autocomplete to populate address fields

  Background:
    Given the home page is opened
    And I navigate to the Autocomplete page

  @regression @autocomplete
  Scenario Outline: Autocomplete fills address fields correctly
    When I enter address "<fullAddress>"
    And I ensure the form is filled with city "<city>", state "<state>" and country "<country>"
    Then the city should be "<city>"
    And the state should be "<state>"
    And the country should be "<country>"


    Examples:
      | fullAddress                                   | city          | state      | country       |
      | 1600 Amphitheatre Parkway, Mountain View, CA   | Mountain View | California | United States |
      | 1 Infinite Loop, Cupertino, CA                 | Cupertino     | California | United States |


