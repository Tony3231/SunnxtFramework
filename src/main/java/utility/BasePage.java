package utility;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;
	Actions act;

	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToBeClickable(int seconds, WebElement locator) {

		wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementToBeVisible(int seconds, WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public void waitForTextToBePresent(int seconds, WebElement locator,String expected_text) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.textToBePresentInElement(locator,expected_text));
	}

	public void waitForDropdownOptions(WebElement dropdownElement, int minimumOptions, int timeoutSeconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
		wait.until(driver -> {
			Select select = new Select(dropdownElement);
			return select.getOptions().size() >= minimumOptions;
		});
	}

	public void implicitWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		System.out.println("Wait for " +seconds +" seconds");
	}

	public void scrollPageWithLoad() {
		js=(JavascriptExecutor)driver;
		long pageHeight = (long) js.executeScript("return document.body.scrollHeight");
		for(int i=0; i<=pageHeight; i+=100) {
			js.executeScript("window.scrollBy(0,300)");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageUntilSizeReached() {
		js=(JavascriptExecutor) driver;

		long pageheight = (long) js.executeScript("return document.body.scrollHeight");

	}

	public void scrollIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

	}

	public void scrollByPixel() {
		js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300);");
		System.out.println("Scrolling........" );

	}

	public void contextClick(WebElement element) {
		act=new Actions(driver);
		act.contextClick(element).build().perform();
	}

	public void waitVisibilityOfElement(WebElement locator, int seconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(locator));
	}

	public void waitVisibilityOfElements(WebElement locator, int seconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(locator));
	}

	public void waitPresenceOfElement(WebElement locator, int seconds) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated((By) locator));
	}

	public boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void moveElement(WebElement element) {
		act.moveToElement(element).build().perform();
	}

}
