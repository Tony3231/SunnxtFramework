package premiumUserContentPlayback;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sunnxtPages.ContentDetailPageSunnxt;
import sunnxtPages.HomepageSunnxt;
import sunnxtPages.LoginPageSunnxt;
import sunnxtPages.TopMenuSunnxt;
import sunnxtPages.baseFunction;
import sunnxtPages.contentLanguageSelection;

public class TC_ContentPlaybackShorts extends baseFunction{

	@BeforeMethod
	public void beforeMethod() {
		setupDriver();
		launchSunnxt();
	}

	@Test(enabled =true)
	public void contentPlayBackCheckTest() throws InterruptedException {
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		//cls.clickAllowButton();
		//cls.clickLanguage("tamil");
		//cls.selectAllLanguages("selectall");
		
		cls.clickDoneButton();

		HomepageSunnxt hp =new HomepageSunnxt(driver);
		hp.clickProfileIcon();
		hp.clickSignInButton();

		LoginPageSunnxt lp=new LoginPageSunnxt(driver);
		lp.userDetail("9841595069", "123456");

		TopMenuSunnxt tm =new TopMenuSunnxt(driver);
		tm.redirectionToShorts();
		implicitWait(30);
		
		hp.contentPlayBackCheck(-1, -1);

		ContentDetailPageSunnxt cdp =new ContentDetailPageSunnxt(driver);
		cdp.clickPlayButton();

		
		
	}

}
