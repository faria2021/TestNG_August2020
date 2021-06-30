package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;







public class LearnTestNG {

	
	private static final String DASHBORAD_BUTTON_LOCATOR = null;
	WebDriver driver;
	String browser = "null";

	
	@BeforeTest
	public void readConfig() {
		Properties prop = new Properties(); 
		//FileReader//InputStream//BufferedReader//Scanner- to read the fill ,this classes can read anykinds of file
	
		try {
			
		InputStream input = new FileInputStream("\\src\\main\\java\\config\\config.properties");
		prop.load(input);
		//prop.getProperty("browser");
	    browser = prop.getProperty("browser");
		System.out.println("used brower: "+ browser);
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	

	}

	@BeforeMethod
	public void init() {

		if(browser.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	        driver = new ChromeDriver();
			
		}else if(browser.equalsIgnoreCase("Firefox")){
			
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
		    driver = new FirefoxDriver();
		}
		
	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test(priority = 1)
	
	public void loginTest() {

	//Element library
		By USERNAME_FIELD_LOCATOR = By.id("username");
		By PASSWORD_FIELD_LOCATOR = By.id("password");
		By SIGNIN_BUTTON_LOCATOR = By.className("login");
		By DASHBOARD_BUTTON_LOCATOR = By.xpath("//span[contains(test(),'Dashboard')]");
				
	//data
		String loginId = "demo@techfios.com";
		String password = "abc123";
		
		driver.findElement(USERNAME_FIELD_LOCATOR).sendKeys(loginId);
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);
        driver.findElement(SIGNIN_BUTTON_LOCATOR).click();
	
	waitForElement(driver, 3, DASHBORAD_BUTTON_LOCATOR);
	String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON_LOCATOR).getText();
	//Assert.assertEquals("Dashboard', dashboardValidationText, ", dashboardValidationTextwrong page");
	}
	@Test(priority = 2)
	public void addCustomerTest() throws InterruptedException {
		 
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON = By.name("login");
		By MENU_BUTTON = By.xpath("//i[@class='fa fa-dedent']");
		By DASHBOARD_BUTTON = By.xpath("//span[contains(test(), 'Dashboard')]");
		By CUSTOMERS_BUTTON = By.xpath("//span[contains(test(), 'Custormers')]");
		By ADD_CUSTOMERS_BUTTON = By.xpath("//a[contains(test(), 'Add Customer']");
		By ADD_CONTACT_LOCATOR = By.xpath("//h5[contains(test(), 'Add Contact']");
		By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
		By COMPANY_NAME_FIELD = By.xpath("//input[@id='company']");		
		By EMAIL_FILED = By.xpath("//input[@id='email']");
		By PHONE_FILED = By.xpath("//input[@id='address']");
		
		By ADDRESS_FIELD = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_REGION_FIELD =By.xpath("//input[@id='state']");
		By ZIP_FIELD = By.xpath("//input[@id='zip']");
		By SUBMIT_BUTTON = By.xpath("//button[@class='btn btn-primary']");
		By LIST_CONTACTS_BUTTON = By.xpath("//a[contains(test(),'List Contacts')]");
		
		
		//Login Data
		String loginId = "demo@techfios.com";	
		String password = "abc123";
		
		//Test Data
		String fullName = "Test August";
		String companyName = "Techfios";
		String emailAddress = "test@gmail.com";
		String phoneNumber = "23165777";
		
		
		
	// perform log in
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGNIN_BUTTON).click();
		
		//validate Dashboard page
		 waitForElement(driver, 3 ,DASHBOARD_BUTTON);
		 String dashboardValidationText = driver.findElement(DASHBOARD_BUTTON).getText();
		 Assert.assertEquals("Dashboard", dashboardValidationText,"wrong page");
		 
		 driver.findElement(CUSTOMERS_BUTTON).click();
		driver.findElement(ADD_CUSTOMERS_BUTTON).click();
		 waitForElement(driver, 3 ,ADD_CONTACT_LOCATOR);
		 
		 //GENERATE RANDOM NUMBER
		  Random rnd = new Random();
		  rnd.nextInt(999);
			int gereratedNo = rnd.nextInt(999); 
		 
		
		 //FILL OUT ADD CUSTORMERS FROM
			driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + gereratedNo);
			driver.findElement(EMAIL_FILED).sendKeys( gereratedNo+emailAddress);
		 
		 
	
		
	}

	
	@AfterMethod
	public void tearDown() {
		driver.close();// what ever we are iniciting over here.what ever object we are creating its
						// closes over here.closes the process.
		// closes the object we are creating those close this.kills the process of the
		// webdriver that one we created.its the kill the process.
		driver.quit();// closes the windows.closes the browser

	}
	private void waitForElement(WebDriver driver, int timeInSeconds, Object dashboradButtonLocator) {
		
		WebDriverWait wait = new WebDriverWait(driver,timeInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By) dashboradButtonLocator));
	
}

}
