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
	@Test(dataProvider = "loginData", dataProviderClass = DataProviderClass.class, enabled = false)
	public void loginTest(String username, String password) {
		
		
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		implicitWait(10);
		//cls.clickAllowButton();
		implicitWait(5);
		cls.clickLanguage("tamil");
		cls.clickLanguage("telugu");
		cls.clickDoneButton();
		HomepageSunnxt hp=new HomepageSunnxt(driver);
		implicitWait(10);
		hp.clickSignIn();
		
		implicitWait(10);
		LoginPageSunnxt lp =new LoginPageSunnxt(driver);
		lp.userDetail("9841595069", "123456");
		
		hp.clickLogout();
	}
	
	@Test (enabled = true)
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
		hp.clickSignIn();

		implicitWait(10);
		LoginPageSunnxt lp =new LoginPageSunnxt(driver);
		lp.userDetail("9841595069", "123456");
		
		hp.clickLogout();

		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		setupDriver();
		launchSunnxt();
	}
	
}
