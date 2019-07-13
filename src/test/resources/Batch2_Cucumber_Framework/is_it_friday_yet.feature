Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  # Scenario: Sunday isn't Friday
  #Given today is "Sunday"
  #When I ask whether it's Friday yet
  #Then I should be told "Nope"
  #
  #Scenario: Friday is Friday
  #Given today is "Friday"
  #When I ask whether it's Friday yet
  #Then I should be told "Yes"
  #
  #Scenario: Monday is not Friday
  #Given today is "Monday"
  #When I ask whether it's Friday yet
  #Then I should be told "Nope"
  
  Scenario Outline: Today is friday or not
    Given today is "<day>"
    When I ask whether it's Friday yet
    Then I should be told "<answer>"

    Examples: 
      | day    | answer |
      | Friday | Yes    |
      | Sunday | Nope   |
      | Monday | Nope   |
