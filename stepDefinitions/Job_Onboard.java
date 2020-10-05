package stepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class Job_Onboard {

	 WebDriver driver; 
	 
	 @Given("^Open a browser$")
	 public void Open_Browser() {
		 
		System.setProperty("webdriver.chrome.driver","C:\\Users\\TulasiJujjavarapu\\Downloads\\chromedriver.exe");
		 
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get("https://alchemy.hguy.co/jobs/wp-admin");
	 }
		 
	@Then("^Login to jobs page$")
	public void Login_page() {	
		
	
		 driver.findElement(By.id("user_login")).sendKeys("root");
		 driver.findElement(By.id("user_pass")).sendKeys("pa$$w0rd");
		 driver.findElement(By.id("wp-submit")).click();
	}
	
	@And("^Locate the left hand menu and Click on Menu Item$")
	public void click_on_left_menu() {
		
		Actions action = new Actions(driver);
		WebElement menu = driver.findElement(By.cssSelector("a.menu-icon-users > div:nth-child(3)"));
		action.moveToElement(menu).build().perform();
		menu.click();
		
	}
		 
	@And("^Click the Add New button$")
	public void locate_the_Add_New_button() {
		driver.findElement(By.cssSelector(".page-title-action")).click();
		//driver.findElement(By.xpath("//a[@class = 'page-title-action'] and [@text = 'Add New']")).click();
	}
	
	@Then("^Fill in the details and submit the form$")
	public void form_details() {
	
		 driver.findElement(By.xpath("//input[@id = 'user_login']")).sendKeys("Ravali_user101");
		 driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("jujja101@gmail.com");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.id("createusersub")).submit();
		 
	}
	
	 @And("^Verify user was created$")
	 public void verify_user() {
		 
		 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.findElement(By.xpath("//input[@id = 'user-search-input']")).sendKeys("Ravali_user123");
		 driver.findElement(By.id("user-search-input")).sendKeys("jujja101@gmail.com");
		
	 }
	 
	 @And("^Close the browser$")
	 public void close_browser() {
		 
		 driver.close();
		 
	 }
	 
	 @Given("^Open a alchemy browser$")
	 public void Open_alchemy_site() {
		 
         System.setProperty("webdriver.chrome.driver","C:\\Users\\TulasiJujjavarapu\\Downloads\\chromedriver.exe");
		 
		 driver = new ChromeDriver();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get("https://alchemy.hguy.co/jobs/");
		 
	 }
	 @Then("^Navigate to Job site$")
     public void navigate_job_site() {
		 driver.findElement(By.xpath("//*[@id = 'menu-item-24']")).click();
    	 

     }
	 @And("^Find the Keywords search input field and change job$")
	 public void keywords_search() {
		 
		 driver.findElement(By.id("search_keywords")).sendKeys("SDET");
		 driver.findElement(By.id("search_location")).sendKeys("Bangalore");
		// driver.findElement(By.className("search_submit")).submit();
		 
	 }
	 
	 @Then("^Find the filter to show only full time jobs$")
	 public void filter_fulltime_jobs() {
		 
		 driver.findElement(By.id("job_type_temporary")).click();
		 driver.findElement(By.id("job_type_freelance")).click();
		 driver.findElement(By.id("job_type_internship")).click();
		 driver.findElement(By.id("job_type_part-time")).click();
		 
	 }
	 @And("^Find the title of job and print$")
	 public void title_of_job() {
		 List<WebElement> jobPostings = driver.findElements(By.xpath("//*[@id=\"post-7\"]/div/div/ul/li/a/div[1]/h3"));
			for (WebElement job : jobPostings) {
				System.out.println(job.getText());
			}
		
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			jobPostings.get(1).click();
			driver.findElement(By.cssSelector(".application_button")).click();
			String message = driver.findElement(By.xpath("/html/body/div/div/div/div/main/article/div/header/div/h1")).getText();
			System.out.println(message);
		 
	 }
	 
	 @Then("^Navigate to post a job page$")
	 public void navigate_job_page() {
		 
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.cssSelector("#menu-item-26 > a:nth-child(1)")).click();
		 driver.findElement(By.cssSelector("a.button")).click();
		 
		 
	 }
	 
	 @And("^Enter \"(.*)\", \"(.*)\" and \"(.*)\"$")
		public void enter_and(String job, String location, String company) throws Throwable {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("job_title")).sendKeys(job);
			driver.findElement(By.id("job_location")).sendKeys(location);
			driver.findElement(By.id("company_name")).clear();
			driver.findElement(By.id("company_name")).sendKeys(company);
			Select selection = new Select(driver.findElement(By.id("job_type")));
			selection.selectByValue("2");
			driver.switchTo().frame("job_description_ifr");
			driver.findElement(By.xpath("/html/body")).sendKeys("New job posting");
			driver.switchTo().defaultContent();
	 }
	 
	 @And("^Click on submit$")
		public void submit_the_form() throws Throwable {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement buttonSubmit = driver.findElement(By.cssSelector("input.button:nth-child(4)"));

			buttonSubmit.click();

			driver.findElement(By.id("job_preview_submit_button")).click();

			
		}

		@Then("^navigate to Job Page$")
		public void go_to_the_Jobs_page() throws Throwable {
			driver.findElement(By.xpath("//*[@id = 'menu-item-24']/a")).click();

		}

		@Then("^Confirm \"(.*)\" listing as shown$")
		public void confirm_job_listing_is_shown_on_page(String job) throws Throwable {
			driver.findElement(By.cssSelector("#search_keywords")).sendKeys(job);
			driver.findElement(By.cssSelector("#post-7 > div > div > form > div.search_jobs > div.search_submit > input[type=submit]")).click();
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			String jobSave = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/main/article/div/div/ul/li[1]/a/div[1]/h3")).getText();
			junit.framework.Assert.assertEquals(job, jobSave);
		}

		@And("^Enter Example \"(.*)\", \"(.*)\" and \"(.*)\"$")
		public void enter_Example_and(String email, String job, String location, String company) throws Throwable {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.id("job_title")).sendKeys(job);
			driver.findElement(By.id("job_location")).sendKeys(location);
			driver.findElement(By.id("company_name")).clear();
			driver.findElement(By.id("company_name")).sendKeys(company);
			Select selection = new Select(driver.findElement(By.id("job_type")));
			selection.selectByValue("2");

			// Switch

			driver.switchTo().frame("job_description_ifr");

			// add description
			driver.findElement(By.xpath("/html/body")).sendKeys("New job posting");

			driver.switchTo().defaultContent();

		}

	 
	 
}
	 
	 
	 
	 

	
	 
	 

