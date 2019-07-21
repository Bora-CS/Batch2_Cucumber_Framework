Feature: Create user profle
  Description: Create user profile feature

  @tag2
  Scenario Outline: user is create the profile with valid data
    Given User is log in with Bora API
      | student@gmail | student123 |
    When User add valid data on their profile
    | handle | Alpha |
    | company | Bora Tech |
    Then User should get a 200 status code and a token

   
