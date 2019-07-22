Feature: Add user experience
  	Add experience details to a user profile

  Scenario: Add experience with valid data
    Given User log into BoraTech API with "maro9090@mail.ru" and "Marat123"
    When User add valid experience data to user's profile
      | title       | Senior Developer |
      | company     | BoraTech         |
      | from        | 2019-4-20        |
    Then User should see updated profile with experience added
      | Status Code |              200 |
      | Title       | Senior Developer |
      
