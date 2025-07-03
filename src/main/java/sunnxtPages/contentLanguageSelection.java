package sunnxtPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class contentLanguageSelection extends BasePage {



	public contentLanguageSelection(WebDriver driver) {
		super(driver);
	}

	@FindBy (id="primaryButton")
	WebElement allowButton;

	@FindBy(id = "secondaryButton")
	WebElement cancelButton;

	@FindBy (xpath = "//div[text()='Tamil']")
	WebElement tamilLanguage;

	@FindBy (xpath = "//div[text()='Telugu']")
	WebElement teluguLanguage;

	@FindBy (xpath = "//div[text()='Malayalam']")
	WebElement malayalamLanguage;

	@FindBy (xpath = "//div[text()='Kannada']")
	WebElement kannadaLanguage;

	@FindBy (xpath = "//div[text()='Hindi']")
	WebElement hindiLanguage;

	@FindBy (xpath = "//div[text()='Bengali']")
	WebElement bengaliLanguage;

	@FindBy (xpath = "//div[text()='Marathi']")
	WebElement marathiLanguage;

	@FindBy (xpath = "//div[text()='English']")
	WebElement englishLanguage;

	@FindBy (xpath= "//button[text()='Done']")
	WebElement doneButton;

	public void clickAllowButton() {
		waitForElementToBeClickable(10, allowButton);
		allowButton.click();
		System.out.println("Notification has been allowed");
	}

	public void ClickCancelButton() {
		waitForElementToBeClickable(10, cancelButton);
		cancelButton.click();
		System.out.println("Notification has been disabled");
	}

	public void clickLanguage(String language) {
		if(language.equalsIgnoreCase("Tamil")) {
			//waitForElementToBeClickable(5,tamilLanguage );
			tamilLanguage.click();
			System.out.println("Tamil Language has been selected");
		}
		else if(language.equalsIgnoreCase("Telugu")) {
			//waitForElementToBeClickable(5,teluguLanguage );
			teluguLanguage.click();
			System.out.println("Telugu Language has been selected");

		}
		else if(language.equalsIgnoreCase("Malayalam")) {
			//	waitForElementToBeClickable(5,malayalamLanguage );
			malayalamLanguage.click();
			System.out.println("Malayalam Language has been selected");
		}
		else if(language.equalsIgnoreCase("Kannada")) {
			//waitForElementToBeClickable(5,kannadaLanguage );
			kannadaLanguage.click();
			System.out.println("Kannada Language has been selected");
		}
		else if(language.equalsIgnoreCase("Hindi")) {
			//waitForElementToBeVisible(5,hindiLanguage );
			hindiLanguage.click();
			System.out.println("Hindi Language has been selected");
		}
		else if(language.equalsIgnoreCase("Bengali")) {
			//waitForElementToBeVisible(5,bengaliLanguage );
			bengaliLanguage.click();
		}
		else if(language.equalsIgnoreCase("Marathi")) {
			//waitForElementToBeVisible(5,marathiLanguage );
			marathiLanguage.click();
		}
		else if(language.equalsIgnoreCase("English")) {
			//waitForElementToBeVisible(5,englishLanguage );
			englishLanguage.click();
		}else {
			System.out.println("Seems language given doesn't listed here");
		}
	}

	public void selectAllLanguages(String all) {
		if(all.equalsIgnoreCase("SelectAll")) {
			tamilLanguage.click();
			teluguLanguage.click();
			malayalamLanguage.click();
			kannadaLanguage.click();
			hindiLanguage.click();
			bengaliLanguage.click();
			marathiLanguage.click();
			englishLanguage.click();
			System.out.println("All Languages has been selected");

		}

	}

	public void clickDoneButton() {
		//waitForElementToBeVisible(5,doneButton );
		doneButton.click();
	}


}
