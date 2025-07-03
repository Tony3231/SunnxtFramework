package premiumUserContentPlayback;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sunnxtPages.ContentDetailPageSunnxt;
import sunnxtPages.HomepageSunnxt;
import sunnxtPages.LoginPageSunnxt;
import sunnxtPages.TopMenuSunnxt;
import sunnxtPages.baseFunction;
import sunnxtPages.contentLanguageSelection;

public class TC_ContentPlaybackComedies extends baseFunction{

	@BeforeMethod
	public void beforemethod() {
		setupDriver();
		launchSunnxt();
	}

	@Test
	private void contentPlaybackCheck() throws InterruptedException {
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		cls.clickLanguage("tamil");
		cls.clickDoneButton();

		HomepageSunnxt hp=new HomepageSunnxt(driver);
		hp.clickProfileIcon();
		hp.clickSignInButton();

		LoginPageSunnxt lp=new LoginPageSunnxt(driver);
		lp.userDetail("9841595069", "123456");

		implicitWait(30);

		TopMenuSunnxt tm =new TopMenuSunnxt(driver);
		tm.redirectionToComedy();

		hp.contentPlayBackCheck(1, -1);

		ContentDetailPageSunnxt cdp =new ContentDetailPageSunnxt(driver);
		cdp.clickPlayButton();
	}

}
