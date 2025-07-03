package premiumUserContentPlayback;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import sunnxtPages.HomepageSunnxt;
import sunnxtPages.LoginPageSunnxt;
import sunnxtPages.baseFunction;
import sunnxtPages.contentLanguageSelection;
import utility.DataProviderClass;

public class TC_Login extends baseFunction {
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class, enabled = true)
	public void loginTest(String username, String password) {
		
		SoftAssert softAssert=new SoftAssert();
		
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		implicitWait(10);
		//cls.clickAllowButton();
		implicitWait(5);
		cls.clickLanguage("tamil");
		cls.clickLanguage("telugu");
		cls.clickDoneButton();
		HomepageSunnxt hp=new HomepageSunnxt(driver);
		implicitWait(10);
		hp.clickProfileIcon();
		hp.clickSignInButton();
		implicitWait(10);
		LoginPageSunnxt lp =new LoginPageSunnxt(driver);
		lp.userDetail(username, password);
		
		try {
			if(lp.isInvalidUserIDVisible()) {
			softAssert.assertTrue(lp.getInvalidUserIDText().contains("Please enter valid email or mobile number"),
					"Email id or mobile number is invalid");	
			}
			
			if(lp.isUserValidationVisible()) {
				softAssert.assertTrue(lp.getUserValidationVisible().contains("User does not exist. Please sign up."), 
						"User does not exist");
			}
			
			if(lp.isInvalidPasswordVisible()) {
				softAssert.assertTrue(lp.getInvalidPasswordVisible().contains("Kindly verify your user id or password and try again."), 
					"Invalid password entered"	);
			}
			
			   if (lp.isEmptyPasswordVisible()) {
			        softAssert.assertTrue(lp.getEmptyPasswordText().contains("Please enter password"),
			            "Empty password message mismatch");
			    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	        softAssert.fail("Exception occurred while validating login error messages: " + e.getMessage());
		}finally {
	        softAssert.assertAll();

		}
		
		hp.clickProfileIcon();
		hp.clickLogout();
	}
	
	@Test (enabled = false)
	public void differntLogin() {
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		implicitWait(10);
		//cls.clickAllowButton();
		implicitWait(5);
		cls.clickLanguage("tamil");
		cls.clickLanguage("telugu");
		cls.clickDoneButton();
		HomepageSunnxt hp=new HomepageSunnxt(driver);
		implicitWait(10);
		hp.clickProfileIcon();
		hp.clickSignInButton();
		implicitWait(10);
		LoginPageSunnxt lp =new LoginPageSunnxt(driver);
		lp.userDetail("username", "password");
		
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		setupDriver();
		launchSunnxt();
	}
	
}
