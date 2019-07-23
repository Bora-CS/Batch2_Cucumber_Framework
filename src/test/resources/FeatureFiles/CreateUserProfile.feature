
Feature: Create User Profile

	Scenario: Happy path
		Given User logged into Bora API 
		When User pass in valid information
			| handle   | Alpha             |
			| company  | Bora Tech         |
			| location | Fairfax, VA       |
			| Status   | Automation Tester |
			| Skills   | HTML, Java				 |
		Then User should get a 200 status code

