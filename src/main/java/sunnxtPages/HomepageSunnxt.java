package sunnxtPages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;
import utility.Log;

public class HomepageSunnxt extends BasePage{

    private static final Logger logger = Log.getLogger(HomepageSunnxt.class);

	public HomepageSunnxt(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath ="//img[@alt='myaccount']")
	WebElement profileIconButton;

	@FindBy (linkText = "Sign In")
	WebElement signInButton;

	@FindBy (xpath = "//li[text()='Logout']")
	private WebElement logoutButton;

	@FindBy (tagName = "h1")
	private List<WebElement> carouselList;

	@FindBy (className = "viewmore_movie_images__2NctY")
	private List<WebElement> content;


	public void clickSignIn() {
		int attempts = 0;
		while (attempts < 2) {
			try {

				waitForElementToBeClickable(10, profileIconButton);
				profileIconButton.click();
                logger.info("Profile icon clicked");
				break;
			}
			catch (StaleElementReferenceException e) {
                logger.warn("Caught StaleElementReferenceException. Retrying...");
			}
			attempts++;
		}

		waitForElementToBeClickable(5, signInButton);
		signInButton.click();
        logger.info("Signin button clicked");
	}

	public void clickLogout() {
		int attempts = 0;
		while (attempts < 2) {
			try {

				waitForElementToBeClickable(10, profileIconButton);
				profileIconButton.click();
                logger.info("Profile icon clicked");
				break;
			}
			catch (StaleElementReferenceException e) {
                logger.warn("Caught StaleElementReferenceException. Retrying...");
			}
			attempts++;
		}
		waitForElementToBeClickable(10, logoutButton);
		logoutButton.click();
        logger.info("Logout button clicked");

	}

	public void contentPlayBackCheck(int carouselIndex, int contentIndex) throws InterruptedException {
		scrollPageWithLoad();
        logger.info("Extracting all carousel elements...");

		List<String> carouselCollections=new ArrayList<String>();

		for(WebElement carousel:carouselList) {
			carouselCollections.add(carousel.getText());
		}

		if(carouselIndex ==-1 && !carouselList.isEmpty()) {
			int totalCarousel = carouselList.size();
			carouselIndex = new Random().nextInt(totalCarousel);
		}

		String specificCarousel = carouselList.get(carouselIndex).getText();

		if(carouselCollections.contains(specificCarousel)) {
			WebElement targetCarousel = carouselList.get(carouselIndex);
			scrollIntoView(targetCarousel);
			targetCarousel.click();
            logger.info("Targeted carousel: " + specificCarousel);

			int maxDepth = 5;
			int clickDepth =0;

			while(clickDepth < maxDepth) {
				List<WebElement> insideCarousel = driver.findElements(By.className("viewmore_movie_images__2NctY"));
				int contentSize = insideCarousel.size();
				
				for (int i = 0; i < 5; i++) {
					scrollByPixel();
					Thread.sleep(5000);
					insideCarousel = driver.findElements(By.className("viewmore_movie_images__2NctY"));
					if (insideCarousel.size() >= 30) {
						break;
					}
				}
				if(contentIndex== -1 && insideCarousel.size()>0) {
					contentIndex = new Random().nextInt(insideCarousel.size());
				}
				if (contentIndex >= insideCarousel.size()) {
                    logger.warn("No content at given index: " + contentIndex);
					break;
				}
				try {
					WebElement contentToClick  = insideCarousel.get(contentIndex);	
					scrollIntoView(contentToClick);
					//System.out.println("Targeted grid index: " +Integer.parseInt(contentToClick.getText().trim()));
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					try {
						contentToClick .click();
						//System.out.println(contentToClick.getText().trim());	
					
					} catch (NoSuchElementException e) {
						e.printStackTrace();
						
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					boolean isCarouselPresent = !driver.findElements(By.className("viewmore_viewall_data__rWzIY")).isEmpty();
					if(isCarouselPresent) {
						clickDepth++;
						contentIndex = -1; // 🔁 Reset for next page
					}else {
						break;
					}
				} catch (Exception e) {
                    logger.error("Exception occurred: ", e);
				}
			}
		}else {
            logger.warn("Provided Carousel isn't listed: " + specificCarousel);
		}
	}
}
