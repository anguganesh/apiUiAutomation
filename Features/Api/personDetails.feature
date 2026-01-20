#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag @API
Feature: Get User details using GET endpoint
  

  @getUserDetails
  Scenario: Verify User details endpoint    
    Given User get User details using 972441
    Then Verify Response code as 200
    Then Verify response data for userDetails endpoint
    
  @getAllUserDetails
  Scenario: Verify All User details endpoint    
    Given User get All User details
    Then Verify Response code as 200
    Then Verify response data for all userDetails endpoint

  
  @addUserData
  Scenario: Add New User data
  	Given User post user details using "samplePostData"
  	Then Verify Response code as 201
  	Then Verify reponse data for post call
  