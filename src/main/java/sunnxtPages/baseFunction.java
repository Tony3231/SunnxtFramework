package sunnxtPages;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utility.ExtentReportManager;
import utility.Log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // << Needed!
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseFunction {

	public WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
    private static final Logger log = Log.getLogger(baseFunction.class);

    @BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}
	
	@AfterSuite
	public void teardownReport() {
		extent.flush();
		//String reportPath = ExtentReportManager.reportPath;
		//EmailUtils.sendTestReport(reportPath);
	}
    
    
    
	public void setupDriver() {


    // Setup ChromeDriver automatically
    WebDriverManager.chromedriver().setup();

    // Configure ChromeOptions
    ChromeOptions options = new ChromeOptions();
    Map<String, Object> prefs = new HashMap<>();
    prefs.put("profile.default_content_setting_values.notifications", 2);
    options.setExperimentalOption("prefs", prefs);

    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--disable-gpu");
    options.addArguments("--remote-allow-origins=*");
    options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());

    // Add headless mode for non-Windows environments
    String os = System.getProperty("os.name").toLowerCase();
    if (!os.contains("win")) {
        options.addArguments("--headless=new"); // Modern headless mode
    }

    // Instantiate WebDriver
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    System.out.println("WebDriver initiated and Chrome launched using WebDriverManager");
}	
	}

	public void launchSunnxt() {
        log.info("Launching Sun NXT.....");   		
		driver.get("https://www.sunnxt.com/");
		
	}

	public void quitDriver() {
		implicitWait(30);
		driver.quit();
		log.info("All browser sessions have been closed.");

	}

	public void closeSession() {
		driver.close();
		log.info("Current session has been closed.");
	}

	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
       log.info("Applying implicit wait of {} seconds.", seconds);

	}

	public String randomEmail() {

		Random random =new Random();
		String randomEmailId = "asgkhbkjk"+Integer.toString(random.nextInt(100))+"@hotfail.com";
		log.debug("Generated random email: {}", randomEmailId);
		return randomEmailId;

	}

	public void notificationBlocker() {
        log.info("Setting Chrome options to block notifications.");
		ChromeOptions options=new ChromeOptions();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
	}

}
