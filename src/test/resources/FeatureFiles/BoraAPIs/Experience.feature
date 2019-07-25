Feature: Add user experience
  	Add experience details to a user profile

  Scenario: Add experience with valid data
    Given User log into BoraTech API
    When User add valid experience data to user's profile
      | title       | Senior Developer |
      | company     | BoraTech         |
      | from        | 2019-04-20        |
    Then User should get a 200
    And User should see updated profile with experience added
