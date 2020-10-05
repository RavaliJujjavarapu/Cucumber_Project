@orange_hrm
Feature: Orange HRM Activities

Scenario: Create Vacancies 
	Given User is on Orange HRM the Login Page 
	When User Navigates to vacancies page and adds Job vacency
	Then Verify the vacancies was created

@orange_hrm	
Scenario: Create candidates 
	Given User is on Orange HRM the Login Page 
	When User Navigates to Candidates page and adds new Candidate
	Then Verify the Candidate was created

@orange_hrm
Scenario: Create multiple Employees
	Given User is on Orange HRM the Login Page
	When Create employee with following firstname and lastname
	|	FirstName	|	LastName 	|
	|	Test123	  |	Last123	  |
	|	Test1234	|	Last1234	|
	|	Test12345	|	Last12345	|
	Then Verify employee with following firstname and lastname
	|	FirstName	|	LastName	|
	| Test123	  |	Last123	  |
	|	Test1234	|	Last1234	|
	|	Test12345	|	Last12345	|

@orange_hrm
Scenario: Create multiple Vacancies
	Given User is on Orange HRM the Login Page
	When Create vacancies with following job tiltle vacancies and hiring manager
	|	Tiltle				|	Vacancies	|	HiringMGr		|
	|	Testing sdet	|		Vacency1 |	 Employee1	|
	|	Testing sdet	|	  Vacency2 |	 Employee2	|
	|	Testing sdet	|		Vacency3 |	 Employee3  |
	Then Verify vacancies with following job tiltle vacancies and hiring manager
	|	Tiltle				|	Vacancies	 |	HiringMGr		|
	|	Testing sdet	|		Vacency1 |	 Employee1	|
	|	Testing sdet	|	  Vacency2 |	 Employee2	|
	|	Testing sdet	|		Vacency3 |	 Employee3  |