<<<<<<< HEAD
Feature: Create user profle
  Description: Create user profile feature

  Scenario: Happy path
		Given User is loged in to Bora API
		When User pass in valid information
			| handle   | Alpha             |
			| company  | Bora Tech         |
			| location | Fairfax, VA       |
			| Status   | Automation Tester |
			| Skills   | HTML, Java				 |
		Then User should get a 200 status code 
#		And USer should see their profile updateS
		

   
=======

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

>>>>>>> branch 'alpha_team' of https://github.com/Bora-CS/Batch2_Cucumber_Framework.git
