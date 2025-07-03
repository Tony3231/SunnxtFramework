package sunnxtPages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class PlayerControlsSunnxt extends BasePage {
	public PlayerControlsSunnxt(WebDriver driver) {
		super(driver);
	}

	@FindBy (id = "player_html5_api")
	private WebElement player;

	@FindBy (xpath = "//button[@title='Fullscreen']")
	private WebElement fullScreen;

	@FindBy (xpath = "//button[@title='Play']")
	private WebElement play;

	@FindBy (xpath = "//button[@title='Pause']")
	private WebElement pause;

	@FindBy (xpath = "//button[@title='Exit Fullscreen']")
	private WebElement exitFullScreen;

	@FindBy (xpath = "//button[@title='Captions']")
	private WebElement captions;

	@FindBy (xpath = "//span[text()='captions off']")
	private WebElement captionsTurnOff;

	@FindBy (xpath = "//span[text()='English']")
	private WebElement captionsTurnOn;

	@FindBy (xpath ="(//div[@class='vjs-menu'])[2]")
	private WebElement qualityOption;
	
	@FindBy (xpath ="//li[text()='720p']")
	private WebElement mediumQuality;
	
	@FindBy (xpath ="//li[text()='1080p']")
	private WebElement highQuality;
	
	@FindBy (xpath ="//li[text()='360p']")
	private WebElement lowQuality;
	
	@FindBy (xpath ="//li[text()='Auto']")
	private WebElement autoQuality;
	
	
	public void qualityChanges(String quality) {
	//	waitForElementToBeClickable(1, qualityOption);
		qualityOption.click();
		implicitWait(3);
		if(quality.equalsIgnoreCase("1080")) {
			highQuality.click();
		}else if (quality.equalsIgnoreCase("720")) {
			mediumQuality.click();
		}else if (quality.equalsIgnoreCase("360")) {
			lowQuality.click();
		}
		else if(quality.equalsIgnoreCase("auto")) {
			autoQuality.click();
		}else {
			System.out.println("Given quality isn't available");
		}
	}
	
	public void enableOrDisableCaptions(String EnableOrDisable) {
		boolean isCaptionDisplayed = captions.isDisplayed();
		if(isCaptionDisplayed) {
			captions.click();
			if(EnableOrDisable.equalsIgnoreCase("Enable")) {
				waitForElementToBeClickable(3, captionsTurnOn);
				captionsTurnOn.click();
			}
			else if (EnableOrDisable.equalsIgnoreCase("Disable")) {
				waitForElementToBeClickable(2, captionsTurnOff);
				captionsTurnOff.click();
			}
			else {
				System.out.println("Invalid Input");
			}
		}
		else {
			System.out.println("Captions button is not visible. Continuing the test.");
		}
	}

	public void exitFullScreen() {
		waitForElementToBeClickable(5, exitFullScreen);
		exitFullScreen.click();
	}

	public void moveFocusToPlayer() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
e.getCause();
		}
		contextClick(player);
	}

	public void fullScreen() {
		waitForElementToBeVisible(2, fullScreen);
		fullScreen.click();
	}

	public void pauseTheContent() {
		waitForElementToBeClickable(2, pause);
		pause.click();
	}

	public void setVolume(double level) {
		if(level<0.1 || level>1.0) {
			System.out.println("Volume must be between 0.1 to 1.0");;
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('player_html5_api').volume = arguments[0];",level);
	}

	public void seekContent(double SeekPosition) {
		JavascriptExecutor js= (JavascriptExecutor) driver;
		Object totalDurationObject = js.executeScript("return document.getElementById('player_html5_api').duration");

		double totalDuration=0.0;
		if(totalDurationObject instanceof Number) {
			totalDuration = ((Number)totalDurationObject).doubleValue();
		}


		if(SeekPosition <= totalDuration) {
			js.executeScript("document.getElementById('player_html5_api').currentTime = arguments[0];", SeekPosition);
		}else {
			System.out.println("Seek position exceeds video duration. Skipping seek.");
		}
	}

}
