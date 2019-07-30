Feature: Create user profle
  Description: Create user profile feature

  Scenario: Happy path
    Given User is logged in via BoraTech APIs
    When User pass  in valid profile information
      | handle   | Alpha             |
      | company  | Bora Tech         |
      | location | Fairfax, VA       |
      | status   | Automation Tester |
      | skills   | HTML, Java        |
    Then User should get a 200
    And User should see their profile updates
      | handle   | Alpha             |
      | company  | Bora Tech         |
      | location | Fairfax, VA       |
      | status   | Automation Tester |
      | skills   | HTML, Java        |

  Scenario Outline: nagitive path
    Given User is logged in via BoraTech APIs
    When User pass  in valid profile information
      | handle   | <handle>   |
      | company  | <company>  |
      | location | <location> |
      | status   | <status>   |
      | skills   | <skills>   |
    Then User should get a <status code>

    Examples: 
      | profileInformation | status code | handle | company | location    | status            | skills     |
      | getCurrentPro      |         200 | Alpha  |         | Fairfax, VA | Automation Tester | HTML, Java |
