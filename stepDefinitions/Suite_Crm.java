package stepDefinitions;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Suite_Crm {
    WebDriver driver;
    WebDriverWait wait;
    Alert alert;

    public void initilize_CRM()
    {
    	System.setProperty("webdriver.chrome.driver","C:\\Users\\TulasiJujjavarapu\\Downloads\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
   
	  @Given("^User is on the CRM Login Page$") 
	  public void CRMlogin() {
	  driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users");
	  driver.findElement(By.id("user_name")).sendKeys("admin");
	  driver.findElement(By.id("username_password")).sendKeys("pa$$w0rd",Keys.ENTER); 
	  }
	  
	  @When("^Count the number of Dashlets$") 
	  public void countDashlets() {
		 wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(@id,'dashlet')]")));
		 List<WebElement> dashletsCount=driver.findElements(By.xpath("//li[contains(@id,'dashlet')]"));
		 System.out.println("Dashlets Count: "+dashletsCount.size());
	  }
	  
	  @Then("^Prints Dashlets number and tiltle$") 
	  public void printDashlets() {
	
		  List<WebElement> dashletsCount=driver.findElements(By.xpath("//li[contains(@id,'dashlet')]"));
		  
		  for(int i=1;i<=dashletsCount.size();i++)
		  {
			  String dashletTiltle=driver.findElement(By.xpath("(//td[@class='dashlet-title']/h3)["+i+"]")).getText();
			  String count=driver.findElement(By.xpath("(//span[@class='pageNumbers'])["+i+"]")).getText();
			  String counts []= count.split("of");
			  System.out.println("Dashlet '"+dashletTiltle+"' is having records '"+counts[1].trim().substring(0, counts[1].length()-2)+"'");
		  }
		 
	  }   
	  
	  @When("^Create Leads with Last Name as \"(.*)\"$")
	    public void createEmployee(String lastName) {
		  	WebElement pimMenu = driver.findElement(By.id("menu_pim_viewPimModule"));
			driver.get(pimMenu.getAttribute("href"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Id")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("actionmenulink")));
			driver.findElement(By.className("actionmenulink")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("last_name")));
			driver.findElement(By.id("last_name")).sendKeys(lastName);
			driver.findElement(By.id("SAVE")).click();
		}
	    
	    @Then("^Verify employee with Last Name as \"(.*)\"$")
	    public void verifyEmployee(String lastName) {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='View Leads']")));
	    	driver.findElement(By.xpath("//div[text()='View Leads']")).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(lastName)));
	    	assertEquals(lastName, driver.findElement(By.linkText(lastName)).getText());
		}
	    
	    
		
	    @When("^Schedule meeting with following users and subject$")
	    public void scheduleMeeting(DataTable table) {
	    	
	    	WebElement meetings = driver.findElement(By.xpath("//a[text()='Meetings']"));
			driver.get(meetings.getAttribute("href"));
			WebElement scheduleMeetingLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-action-name='Schedule_Meeting']")));
			driver.get(scheduleMeetingLink.getAttribute("href"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("actionmenulink")));
  	  		driver.findElement(By.className("actionmenulink")).click();
			List<List<String>> data = table.asLists();
			int rows=data.size();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
			driver.findElement(By.id("name")).sendKeys(data.get(1).get(1));
			for(int i=1;i<rows;i++)
			{
				searchLNandAdd(data.get(i).get(0));
			}
			driver.findElement(By.id("SAVE_HEADER")).click();
		}
	    
	    public void searchLNandAdd(String ln)
	    {
	    	driver.findElement(By.id("search_last_name")).clear();
			driver.findElement(By.id("search_last_name")).sendKeys(ln);
			driver.findElement(By.id("invitees_search")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("invitees_add_1")));
			driver.findElement(By.id("invitees_add_1")).click();
		}
	    
	    @Then("^Verify Scheduled meeting with following subject$")
	    public void verifymeeting(DataTable table) {
	    	List<List<String>> data = table.asLists();
			driver.findElement(By.xpath("//div[text()='View Meetings']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(data.get(1).get(0))));
			assertEquals(data.get(1).get(0), driver.findElement(By.linkText(data.get(1).get(0))).getText());
		}
	  
	   
	    @When("^Create product with product name as \"(.*)\" and price as \"(.*)\"$")
	    public void create_product_with_product_name_as(String ProductName, String Price) {
	    	WebElement productsLink = driver.findElement(By.xpath("//a[text()='Products']"));
			driver.get(productsLink.getAttribute("href"));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='Assign']")));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("actionmenulink")));
  	  		driver.findElement(By.className("actionmenulink")).click();
  	  		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("name")));
  	  		driver.findElement(By.id("name")).sendKeys(ProductName);
			driver.findElement(By.id("price")).sendKeys(Price);
			driver.findElement(By.id("SAVE")).click();
		}

	    @Then("^Verify product \"(.*)\" listed$")
	    public void verify_product_listed(String ProductName) {
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='View Products']")));
	    	driver.findElement(By.xpath("//div[text()='View Products']")).click();
	    	wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(ProductName)));
			assertEquals(ProductName, driver.findElement(By.linkText(ProductName)).getText());
	    }
	   
	    
	    @After("@suite_crm")
	    public void closeBrowser_CRM()
	    {
	    	driver.close();
	    }
	    
		
		  
}