package sunnxtPages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class ContentDetailPageSunnxt extends BasePage {

	public ContentDetailPageSunnxt(WebDriver driver) {
		super(driver);
	}

	@FindBy (id = "paly_button")
	private WebElement playButton;

	@FindBy (xpath = "(//li[text()='Watchlist'])[2]")
	private WebElement WatchlistButton;

	@FindBy (xpath = "//div[text()='Play']")
	private WebElement playBtnLiveTV;

	@FindBy (xpath = "//div[text()='Explore EPG']")
	private List<WebElement> exploreEpg;

	public void clickPlayButton() {
		System.out.println("Wait until content plays..........");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(isElementPresent(playBtnLiveTV)) {
			playBtnLiveTV.click();
		}else if(isElementPresent(playButton)) {
			playButton.click();
		}
	}

}
