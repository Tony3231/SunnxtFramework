package sunnxtPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.BasePage;

public class LoginPageSunnxt extends BasePage{

	public LoginPageSunnxt(WebDriver driver) {
		super(driver);
	}

	@FindBy (name = "email")
	WebElement userIdField;

	@FindBy (name = "password")
	WebElement passwordFied;

	@FindBy (xpath = "//button[@class='signin_signin_btn__tHSgj btn btn-primary']")
	WebElement loginButton;

	@FindBy (xpath = "//a[text()='SIGN UP']")
	WebElement SignupButton;

	@FindBy (xpath = "//a[text()='Forgot Password ?']")
	WebElement forgotPasswordButton;

	@FindBy (xpath = "//p[text()='User does not exist. Please sign up.']")
	private WebElement uservalidation;

	@FindBy (xpath ="//p[text()='Please enter valid email or mobile number']")
	private WebElement invalidUserID;

	@FindBy (xpath = "//p[text()='Kindly verify your user id or password and try again.']")
	private WebElement invalidPassword;

	@FindBy (xpath = "//p[text()='Please enter password']")
	private WebElement emptyPasswordField;

	public void userDetail(String username, String password) {

		implicitWait(10);
		userIdField.sendKeys(username);
		System.out.println("Email or Mobile Number has been entered");
		passwordFied.sendKeys(password);
		System.out.println("Password has been entered");
		loginButton.click();
		
		

//	    try {
//	        if (isElementPresent(invalidUserID)) {
//	            String msg = invalidUserID.getText();
//	            if (msg.contains("Please enter valid email or mobile number")) {
//	                System.out.println("Email id or mobile number is invalid: " + msg);
//	            }
//	            
//	            
//	        } else if (isElementPresent(uservalidation)) {
//	            String msg = uservalidation.getText();
//	            if (msg.contains("User does not exist. Please sign up.")) {
//	                System.out.println("User does not exist: " + msg);
//	            }
//	        } else if (isElementPresent(invalidPassword)) {
//	            String msg = invalidPassword.getText();
//	            if (msg.contains("Kindly verify your user id or password and try again.")) {
//	                System.out.println("Invalid password entered: " + msg);
//	            }
//	        } else if (isElementPresent(invalidUserID) && isElementPresent(emptyPasswordField)) {
//	            System.out.println("Both Email ID and Password fields are empty.");
//	        } else if (isElementPresent(emptyPasswordField)) {
//	            String msg = emptyPasswordField.getText();
//	            if (msg.contains("Please enter password")) {
//	                System.out.println("Password field is empty: " + msg);
//	            }
//	        } else {
//	            System.out.println("User login successful with username: " + username);
//	        }
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        System.out.println("Exception while validating login response: " + e.getMessage());
//	    }

	}

	public void signupPageRedirection() {
		String expectedValidation = uservalidation.getText();
		if(expectedValidation.equalsIgnoreCase(expectedValidation)) {
			waitForElementToBeClickable(20, SignupButton);
			SignupButton.click();
		}else {
			System.out.println("User already exists");
		}
	}

	public void forgotPasswordPageRedirection() {
		forgotPasswordButton.click();
	}
	
	public boolean isInvalidUserIDVisible() {
		return isElementPresent(invalidUserID);
	}
	
	public String getInvalidUserIDText() {
		return invalidUserID.getText();
	}
	
	public boolean isUserValidationVisible() {
		return isElementPresent(uservalidation);
	}
	
	public String getUserValidationVisible() {
		return uservalidation.getText();
	}
	
	public boolean isInvalidPasswordVisible() {
		return isElementPresent(invalidPassword);
	}
	
	public String getInvalidPasswordVisible() {
		return invalidPassword.getText();
	}
	public boolean isEmptyPasswordVisible() {
	    return isElementPresent(emptyPasswordField);
	}

	public String getEmptyPasswordText() {
	    return emptyPasswordField.getText();
	}
}
