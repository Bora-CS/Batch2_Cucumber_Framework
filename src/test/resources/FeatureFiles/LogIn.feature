Feature: Log In
  Description: Log in feature will log in a user with username and password 
  					 and return a token when successful

  Scenario: Happy Path
    Given User is registered with Bora API
    When User submits the login request with valid username and valid password
      | maro9090@mail.ru | Marat123 |
    Then User should get a 200 status code and a token

  Scenario Outline: Negative Path
    When User submits the login request with "<username>" and "<password>"
    Then User should get a <status code> status code and <an error message>

    Examples: 
      | username |  password | status code | an error message |
      