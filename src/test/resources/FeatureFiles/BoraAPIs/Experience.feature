Feature: Add user experience
  	Add experience details to a user profile

  #Scenario: Add experience with valid data
  #Given User log into BoraTech API
  #When User add valid experience data to user's profile
  #| title   | Senior Developer |
  #| company | BoraTech         |
  #| from    | 2019-04-20       |
  #Then User should get a 200
  #And User should see updated profile with experience added
  
  Scenario Outline: Add experience with valid data
    Given User log into BoraTech API
    When User add valid experience data to user's profile
      | typeOfRequest | <typeOfRequest> |
      | current       | <current>       |
      | title         | <title>         |
      | company       | <company>       |
      | location      | <location>      |
      | from          | <from>          |
      | to            | <to>            |
      | description   | <description>   |
    Then User should get a <status code>
    And User should see updated profile with experience added

    Examples: 
      | typeOfRequest | status code | current | title                   | company  | location    | from       | to         | description                            |
      | addExperience |         200 | true    | Senior Technical Writer | BoraTech | Fairfax, VA | 2019-04-20 | 2019-07-24 | I dont know what to say about this job |
