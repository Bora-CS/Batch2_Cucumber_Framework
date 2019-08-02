Feature: Amazon UI Tests
	
#	@UI_Test @Regression
  #Scenario Outline: Search for fuctionality
    #Given User is on amazon homepage
    #When User search for "<item>"
    #Then User should see results that related to "<item>"
#
    #Examples: 
      #| item               |
      #| Japanese hair mask |
      #| iPhone Case        |
      #| Pen                |
      #| Charger            |
      #| Mac Book           |

 Scenario Outline: Search item by  name
   Given I'm on the amazon.com homepage
   When I search for an "<Item>"
   And I get some results related to "<Item>"
   Then I will be able to find out the highest price, lowset price and the average price
   Examples:
     | Item        |
     | Shampoo     |
     | Soap        |
     | Conditioner |