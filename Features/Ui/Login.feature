@Login @UI
Feature: Login Feature Testing
  
  Background:
		Given User has to login first
  
  @tag10 
  Scenario: Login Feature scenario - 1
    Given User navigate to "https://www.youtube.com/"
    
  @tag20 @Test
  Scenario: Login Feature scenario - 2  
    Given User navigate to "https://www.gmail.com/"
    

    