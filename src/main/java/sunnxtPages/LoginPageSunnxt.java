package sunnxtPages;



import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utility.BasePage;
import utility.Log;

public class LoginPageSunnxt extends BasePage{

    private static final Logger logger = Log.getLogger(LoginPageSunnxt.class);

	
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
        logger.info("Email or Mobile Number has been entered");
        
		passwordFied.sendKeys(password);
        logger.info("Password has been entered");
        
		loginButton.click();
        logger.info("Login button clicked");

		

	    try {
	        if (isElementPresent(invalidUserID)) {
	            String msg = invalidUserID.getText();
	            if (msg.contains("Please enter valid email or mobile number")) {
                    logger.warn("Invalid email or mobile: " + msg);
	            }
	        } else if (isElementPresent(uservalidation)) {
	            String msg = uservalidation.getText();
	            if (msg.contains("User does not exist. Please sign up.")) {
                    logger.warn("User not found: " + msg);
	            }
	        } else if (isElementPresent(invalidPassword)) {
	            String msg = invalidPassword.getText();
	            if (msg.contains("Kindly verify your user id or password and try again.")) {
                    logger.warn("Incorrect password: " + msg);
	            }
	        } else if (isElementPresent(invalidUserID) && isElementPresent(emptyPasswordField)) {
	        	 logger.warn("Both Email ID and Password fields are empty." );
	        } else if (isElementPresent(emptyPasswordField)) {
	            String msg = emptyPasswordField.getText();
	            if (msg.contains("Please enter password")) {
	            	 logger.warn("Password field is empty: " + msg);
	            }
	        } else {
                logger.info("User login successful with username: " + username);
	        }

	    } catch (Exception e) {
            logger.error("Exception during login validation: ", e);
 }

	}

	public void signupPageRedirection() {
		String expectedValidation = uservalidation.getText();
		if(expectedValidation.equalsIgnoreCase(expectedValidation)) {
			waitForElementToBeClickable(20, SignupButton);
			SignupButton.click();
			logger.info("Redirected to Sign Up page");
			
		}else {
			logger.info("User already exists");
		}
	}

	public void forgotPasswordPageRedirection() {
		forgotPasswordButton.click();
		logger.info("Redirected to Forget Password page");
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
