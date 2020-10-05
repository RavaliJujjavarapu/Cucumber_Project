package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Orange_hrm {
    WebDriver driver;
    WebDriverWait wait;
    Alert alert;

    public void initilize_HRM()
    {
		 System.setProperty("webdriver.chrome.driver","C:\\Users\\TulasiJujjavarapu\\Downloads\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    

	  @Given("^User is on Orange HRM the Login Page$") 
	  public void OrangeHRMlogin() {
	  driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login");
	  driver.findElement(By.id("txtUsername")).sendKeys("orange");
	  driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123",Keys.ENTER); 
	  }
	  
	  @When("^User Navigates to vacancies page and adds Job vacency$") 
	  public void addVacancies() {
		  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Recruitment")));
		  driver.findElement(By.linkText("Recruitment")).click();
		  driver.findElement(By.linkText("Vacancies")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.name("btnAdd")));
		  driver.findElement(By.name("btnAdd")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("addJobVacancy_jobTitle")));
		  Select jobTiltle = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		  jobTiltle.selectByVisibleText("Automation Test Engineer");
		  driver.findElement(By.id("addJobVacancy_name")).sendKeys("Vacency123");
		  driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Employee");
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'ac_even')]")));
		  driver.findElement(By.xpath("//li[contains(@class,'ac_even')]")).click();
		  driver.findElement(By.id("btnSave")).click();
		}
	  
	  @When("^Create vacancies with following job tiltle vacancies and hiring manager$") 
	  public void addMultipleVacancies(DataTable table) {
		  List<List<String>> data = table.asLists();
			int rows=data.size();
			
			for(int i=1;i<rows;i++)
			{
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Recruitment")));
				driver.findElement(By.linkText("Recruitment")).click();
				driver.findElement(By.linkText("Vacancies")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.name("btnAdd")));
				driver.findElement(By.name("btnAdd")).click();
				wait.until(ExpectedConditions.elementToBeClickable(By.id("addJobVacancy_jobTitle")));
				Select jobTiltle = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
				jobTiltle.selectByVisibleText(data.get(i).get(0));
				driver.findElement(By.id("addJobVacancy_name")).sendKeys(data.get(i).get(1));
				driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys(data.get(i).get(2));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'ac_even')]")));
				driver.findElement(By.xpath("//li[contains(@class,'ac_even')]")).click();
				driver.findElement(By.id("btnSave")).click();
				
			}
	  }
	  
	  @Then("^Verify the vacancies was created$") 
	  public void VerifyVacancies() {
	  
	  driver.findElement(By.id("btnBack")).click();
	  Select jobTiltle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
	  jobTiltle.selectByVisibleText("Automation Test Engineer");
	  Select hiringMgr = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
	  hiringMgr.selectByVisibleText("Test Employee");
	  driver.findElement(By.id("btnSrch")).click();
	  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Test Vacency123")));
	  
	  if( driver.findElement(By.linkText("Test Vacency123")).getText().equals("Test Vacency123"))
		{
			System.out.println("Vacancy Successfully Added");
		}else
		{
			System.out.println("Vacancy not Added");
		}
		 
		 
	  }   
	  
	  @Then("^Verify vacancies with following job tiltle vacancies and hiring manager$") 
	  public void VerifyMultipleVacancies(DataTable table) {
		  List<List<String>> data = table.asLists();
		  int rows=data.size();
		  driver.findElement(By.id("btnBack")).click();
			for(int i=1;i<rows;i++)
			{
				  Select jobTiltle = new Select(driver.findElement(By.id("vacancySearch_jobTitle")));
				  jobTiltle.selectByVisibleText(data.get(i).get(0));
				  Select hiringMgr = new Select(driver.findElement(By.id("vacancySearch_hiringManager")));
				  hiringMgr.selectByVisibleText(data.get(i).get(2));
				  driver.findElement(By.id("btnSrch")).click();
				  wait.until(ExpectedConditions.elementToBeClickable(By.linkText(data.get(i).get(1))));
				  if( driver.findElement(By.linkText(data.get(i).get(1))).getText().equals(data.get(i).get(1)))
					{
						System.out.println("Success");
					}else
					{
						System.out.println("Fail");
					}
			}
		  }   
	  
	  @When("^User Navigates to Candidates page and adds new Candidate$") 
	  public void addCandidate() {
		 wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Recruitment")));
		 driver.findElement(By.linkText("Recruitment")).click();
		 driver.findElement(By.linkText("Candidates")).click();
		 wait.until(ExpectedConditions.elementToBeClickable(By.name("btnAdd")));
		 driver.findElement(By.name("btnAdd")).click();
		 driver.findElement(By.id("addCandidate_firstName")).sendKeys("Test FN21");
		 driver.findElement(By.id("addCandidate_lastName")).sendKeys("Test LN21");
		 driver.findElement(By.id("addCandidate_email")).sendKeys("test312@yopmail.com");
		 driver.findElement(By.id("addCandidate_resume")).sendKeys(System.getProperty("user.dir")+"\\Sample.docx");
		 driver.findElement(By.id("btnSave")).click();
		}
	  
	  @Then("^Verify the Candidate was created$") 
	  public void VerifyCandidate() {
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("btnBack")));
		  driver.findElement(By.id("btnBack")).click();
		  driver.findElement(By.id("candidateSearch_candidateName")).sendKeys("Test FN21 Test LN21");
		  wait.until(ExpectedConditions.elementToBeClickable(By.id("candidateSearch_candidateName")));
		  driver.findElement(By.xpath("//li[contains(@class,'ac_even')]")).click();
		  driver.findElement(By.id("btnSrch")).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Test FN21 Test LN21")));
		  
		  if( driver.findElement(By.linkText("Test FN21 Test LN21")).getText().equals("Test FN21 Test LN21"))
		  	{
			  System.out.println("Candidate Successfully Added");
		  	}
		  else{
			System.out.println("Candidate not Added");
		  }
	 }   
    
      @When("^Create employee with following firstname and lastname$")
	  public void createEmployee(DataTable table)
	  {
		  List<List<String>> data = table.asLists();
		  int rows=data.size();
		  for(int i=1;i<rows;i++)
		  {
			  wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_pim_viewPimModule")));
			  driver.findElement(By.id("menu_pim_viewPimModule")).click();
			  driver.findElement(By.linkText("Employee List")).click();
			  wait.until(ExpectedConditions.elementToBeClickable(By.name("btnAdd")));
			  driver.findElement(By.name("btnAdd")).click(); 
			  wait.until(ExpectedConditions.elementToBeClickable(By.id("firstName")));
			  driver.findElement(By.id("firstName")).sendKeys(data.get(i).get(0));
			  driver.findElement(By.id("lastName")).sendKeys(data.get(i).get(1));
			  driver.findElement(By.id("btnSave")).click();
			}
		}
	    
	  
	  
	  	@Then("^Verify employee with following firstname and lastname$")
	    public void verifyEmployee(DataTable table) {
	  		List<List<String>> data = table.asLists();
			int rows=data.size();
			  for(int i=1;i<rows;i++)
			  {
				  	driver.findElement(By.linkText("Employee List")).click();
				  	wait.until(ExpectedConditions.elementToBeClickable(By.id("empsearch_employee_name_empName")));
				  	driver.findElement(By.id("empsearch_employee_name_empName")).sendKeys(data.get(i).get(0)+" "+data.get(i).get(1)); 
					driver.findElement(By.xpath("//li[contains(@class,'ac_even')]")).click();
					driver.findElement(By.id("searchBtn")).click(); 
					wait.until(ExpectedConditions.elementToBeClickable(By.linkText(data.get(i).get(0))));
					assertEquals(data.get(i).get(0),driver.findElement(By.linkText(data.get(i).get(0))).getText());
					assertEquals(data.get(i).get(1),driver.findElement(By.linkText(data.get(i).get(1))).getText());
			  }
			}

	    public void close_Browser()
	    {
	    	driver.close();
	    }
	    
		
		  
	  
        
}