package sunnxtPages;

import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;
import utility.Log;

public class PlayerControlsSunnxt extends BasePage {
	public PlayerControlsSunnxt(WebDriver driver) {
		super(driver);
	}

    private static final Logger logger = Log.getLogger(PlayerControlsSunnxt.class);

	
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
        logger.info("Opened quality settings");
		if(quality.equalsIgnoreCase("1080")) {
			highQuality.click();
			logger.info("Changed quality to 1080p");
		}else if (quality.equalsIgnoreCase("720")) {
			mediumQuality.click();
            logger.info("Changed quality to 720p");
		}else if (quality.equalsIgnoreCase("360")) {
			lowQuality.click();
            logger.info("Changed quality to 360p");
		}
		else if(quality.equalsIgnoreCase("auto")) {
			autoQuality.click();
            logger.info("Set quality to Auto");
		}else {
            logger.warn("Invalid quality option provided: " + quality);
		}
	}
	
	public void enableOrDisableCaptions(String EnableOrDisable) {
		boolean isCaptionDisplayed = captions.isDisplayed();
		if(isCaptionDisplayed) {
			captions.click();
            logger.info("Captions button clicked");

			if(EnableOrDisable.equalsIgnoreCase("Enable")) {
				waitForElementToBeClickable(3, captionsTurnOn);
				captionsTurnOn.click();
                logger.info("Captions enabled");

			}
			else if (EnableOrDisable.equalsIgnoreCase("Disable")) {
				waitForElementToBeClickable(2, captionsTurnOff);
				captionsTurnOff.click();
                logger.info("Captions disabled");

			}
			else {
                logger.warn("Invalid caption option provided: " + EnableOrDisable);
			}
		}
		else {
            logger.warn("Captions button not visible. Skipping caption change.");
		}
	}

	public void exitFullScreen() {
		waitForElementToBeClickable(5, exitFullScreen);
		exitFullScreen.click();
        logger.info("Exited fullscreen mode");

	}

	public void moveFocusToPlayer() {
//		try {
//			Thread.sleep(5000);
//		} catch (Exception e) {
//e.getCause();
//		}
//		contextClick(player);
		
		try {
            Thread.sleep(5000);
            contextClick(player);
            logger.info("Focused on player and performed context click");
        } catch (Exception e) {
            logger.error("Error while moving focus to player", e);
        }
		
	}

	public void fullScreen() {
		waitForElementToBeVisible(2, fullScreen);
		fullScreen.click();
        logger.info("Entered fullscreen mode");

	}

	public void pauseTheContent() {
		waitForElementToBeClickable(2, pause);
		pause.click();
        logger.info("Paused the content");

	}

	public void setVolume(double level) {
		if(level<0.1 || level>1.0) {
            logger.warn("Volume level must be between 0.1 and 1.0. Provided: " + level);
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('player_html5_api').volume = arguments[0];",level);
        logger.info("Volume set to: " + level);

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
            logger.info("Seeked content to position: " + SeekPosition + "s");

		}else {
            logger.warn("Seek position exceeds video duration. Skipping seek.");
		}
	}

}
