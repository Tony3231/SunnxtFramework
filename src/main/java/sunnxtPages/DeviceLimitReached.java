package sunnxtPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class DeviceLimitReached extends BasePage{

	public DeviceLimitReached(WebDriver driver) {
		super(driver);
	}

	@FindBy (tagName = "h3")
	private WebElement deviceLimitPopup;
	
	@FindBy (xpath = "//button[text()='Add Device']")
	private WebElement addDeviceButton;
	
	@FindBy (xpath = "//button[text()='Manage Devices']")
	private WebElement manageDeviceButton;
	

	
}
