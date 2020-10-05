@suite_crm
Feature: CRM Activities


Scenario: Count Dashlets
	Given User is on the CRM Login Page
	When Count the number of Dashlets
	Then Prints Dashlets number and tiltle

@suite_crm
Scenario Outline: create Leads
	Given User is on the CRM Login Page
	When Create Leads with Last Name as "<LastName>"
	Then Verify employee with Last Name as "<LastName>"

Examples:
|	LastName	|
|	LastName14	|
|	LastName24	|
|	LastName34	|
	

@suite_crm
Scenario: Schedule Meeting
	Given User is on the CRM Login Page
	When Schedule meeting with following users and subject
	|	LastName	|	Subject			|
	|	LastName14	|	TestSubject1	|
	|	LastName24	|	TestSubject1	|
	|	LastName34	|	TestSubject1	|	
	Then Verify Scheduled meeting with following subject 
	|	Subject			|
	|	TestSubject1	|

@auite_crm
Scenario Outline: Schedule Meeting
	Given User is on the CRM Login Page
	When Create product with product name as "<ProductName>" and price as "<Price>"
	Then Verify product "<ProductName>" listed

Examples:
|	ProductName		|	Price	|
|	Test Product12	|	2500	|