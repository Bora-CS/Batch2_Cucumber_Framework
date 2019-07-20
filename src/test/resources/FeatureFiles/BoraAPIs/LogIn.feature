Feature: Log In
  Description: Log in feature will log in a user with username and password 
  					 and return a token when successful

  Scenario: Happy Path
    Given User is registered with Bora API
    When User submits the login request with valid username and valid password
      | maro9090@mail.ru | Marat123 |
    Then User should get a 200 status code and a token

  Scenario: Invalid Username with Valid Password
    Given User is registered with Bora API
    When User submits the login request with invalid username and valid password
      | maro9090 | Marat123 |
    Then User should get a 400 status code and an error message
      | email | Email is invalid |
      
  Scenario: Valud Username with Invalid Password
    Given User is registered with Bora API
    When User submits the login request with valid username and invalid password
      | maro9090@mail.ru | arat123 |
    Then User should get a 400 status code and an error message
      | password | Password incorrect |
