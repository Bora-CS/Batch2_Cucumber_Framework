Feature: Log In
  Description: Log in feature will log in a user with username and password 
  					 and return a token when successful

  Scenario: Happy Path - log in with valid data
    Given User is registered with Bora API
    When User submits the login request with "maro9090@mail.ru" and "Marat123"
    Then User should get a 200 status code and a token

  Scenario Outline: Negative Scenarios - log in with invalid data
    Given User is registered with Bora API
    When User submits the login request with "<username>" and "<password>"
    Then User should get a <status code> and an "<error message>"

    Examples: 
      | username           | password | status code | error message                   |
      | maro9090           | Marat123 |         400 | email - Email is invalid        |
      | maro9090@mail.ru   | arat123  |         400 | password - Password incorrect   |
      | maro9090           | arat123  |         400 | email - Email is invalid        |
      | hellobro@gmail.com |    12345 |         404 | email - User not found          |
      |                    | Marat123 |         400 | email - Email field is required |
      | maro9090@mail.ru   |          |         400 | password - Password field is required   |
