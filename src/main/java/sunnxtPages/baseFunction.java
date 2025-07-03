package sunnxtPages;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class baseFunction {

	public WebDriver driver;
	public void setupDriver() {
		ChromeOptions options=new ChromeOptions();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);

		System.setProperty("webdriver.driver.chrome", "C:\\Users\\arunachalam.d\\eclipse-workspace\\TestNGFramework\\chromedriver\\chromedriver.exe");
		driver=new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("Chrome browser launched successfully");
	}

	public void launchSunnxt() {
		driver.get("https://www.sunnxt.com/");
		System.out.println("SunNXT has been launched Successfully");
	}

	public void quitDriver() {
		implicitWait(30);
		driver.quit();
		System.out.println("All session has been closed");
	}

	public void closeSession() {
		driver.close();
		System.out.println("Current Session has been closed");
	}

	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		System.out.println("Wait for few seconds");
	}

	public String randomEmail() {

		Random random =new Random();
		String randomEmailId = "asgkhbkjk"+Integer.toString(random.nextInt(100))+"@hotfail.com";
		return randomEmailId;

	}

	public void notificationBlocker() {
		ChromeOptions options=new ChromeOptions();
		Map<String, Object> prefs=new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
	}

}
