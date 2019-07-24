
Feature: Create user profle
  Description: Create user profile feature

  Scenario: Happy path
		Given User is logged in via BoraTech APIs
		When User pass in valid profile information
			| handle   | Alpha             |
			| company  | Bora Tech         |
			| location | Fairfax, VA       |
			| status   | Automation Tester |
			| skills   | HTML, Java				 |
		Then User should get a 200 status code 
		And User should see their profile updates
		

   
