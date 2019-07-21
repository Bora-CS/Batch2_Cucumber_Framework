
Feature: Create User Profile
  


  Scenario: Happy path
    Given User logged in postman
    When User create user profile with valid data
    Then User should see 200 statu code
   

  
