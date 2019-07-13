Feature: Amazon UI Tests

  Scenario Outline: Search for fuctionality
    Given User is on amazon homepage
    When User search for "<item>"
    Then User should see results that related to "<item>"

    Examples: 
      | item               |
      | Japanese hair mask |
      | iPhone Case        |
      | Pen                |
      | Charger            |
      | Mac Book           |
