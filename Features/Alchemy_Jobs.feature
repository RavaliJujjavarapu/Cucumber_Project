
@new_user
Feature: Testing Job Board Page

  Scenario: create a new user
    Given Open a browser
    Then Login to jobs page
    And Locate the left hand menu and Click on Menu Item
    And Click the Add New button
    Then Fill in the details and submit the form
    And Verify user was created
    And Close the browser
    
@new_user
  Scenario: Searching for Jobs using xpath
    Given Open a alchemy browser
    Then Navigate to Job site
    And Find the Keywords search input field and change job
    Then Find the filter to show only full time jobs
    And Find the title of job and print
    And Close the browser

@new_user    
  Scenario: Posting a job using parameterization
   Given Open a broswer
   Then Navigate to post a job page 
   And Enter "Testing_SDET", "Bangalore", and "Hiring_process"
   And Click on submit
   Then navigate to Job Page
   And Confirm "Testing_SDET" Lisiting as shown
   And Close the browser
   
@new_user 
  Scenario Outline: Posting job using table 
   Given Open a broswer
   Then Navigate to post a job page 
   And Enter Example "<Title>", "<Location>", and "<Description>"
   And Click on submit
   Then navigate to Job Page
   And Confirm "<Title>" Lisiting 
   And Close the browser
   
  Examples:
   |Title| |Location| |Description|
   |Testing_SDET| |Bangalore| |hiring process|
   
   