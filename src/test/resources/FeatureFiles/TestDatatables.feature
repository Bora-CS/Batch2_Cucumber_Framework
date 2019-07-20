Feature: Running tests with data tables

	@Smoke
  Scenario: Using datatables for steps
    Given User is registered with amazon.com
      | 1 | muradil@gmail.com | murad123 |
      | 2 | marat@gmail.com   | marat123 |
    When User logs in on amazon homepage
    Then User should be able
