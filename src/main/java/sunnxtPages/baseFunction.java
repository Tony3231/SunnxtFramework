package sunnxtPages;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import utility.Log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; // << Needed!
public class baseFunction {

	public WebDriver driver;
    private static final Logger log = Log.getLogger(baseFunction.class);

	
	public void setupDriver() {
		ChromeOptions options=new ChromeOptions();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.driver.chrome", "C:\\Users\\arunachalam.d\\eclipse-workspace\\TestNGFramework\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		log.info("Web Driver initiated. Launching Chrome Browser");

		
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
      //  log.info("Applying implicit wait of {} seconds.", seconds);

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
