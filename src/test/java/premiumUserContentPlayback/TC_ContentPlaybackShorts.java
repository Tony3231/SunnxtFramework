package premiumUserContentPlayback;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import sunnxtPages.ContentDetailPageSunnxt;
import sunnxtPages.HomepageSunnxt;
import sunnxtPages.LoginPageSunnxt;
import sunnxtPages.TopMenuSunnxt;
import sunnxtPages.baseFunction;
import sunnxtPages.contentLanguageSelection;
import utility.ExtentReportManager;
import utility.Log;

public class TC_ContentPlaybackShorts extends baseFunction{
    private static final Logger logger = Log.getLogger(TC_ContentPlaybackShorts.class);

	@BeforeMethod
	public void beforeMethod() {
		setupDriver();
		launchSunnxt();
	}

	@Test(enabled =true)
	public void contentPlayBackCheckTest() throws InterruptedException {
		logger.info("Starting playback testing for Shorts Section");
		test = ExtentReportManager.createTest("Shorts Section Playback");
		contentLanguageSelection cls=new contentLanguageSelection(driver);
		//cls.clickAllowButton();
		//cls.clickLanguage("tamil");
		cls.selectAllLanguages("selectall");
		
		cls.clickDoneButton();

		HomepageSunnxt hp =new HomepageSunnxt(driver);
		implicitWait(10);
		hp.clickSignIn();

		LoginPageSunnxt lp=new LoginPageSunnxt(driver);
		lp.userDetail("9841595069", "123456");

		TopMenuSunnxt tm =new TopMenuSunnxt(driver);
		tm.redirectionToShorts();
		implicitWait(30);
		
		hp.contentPlayBackCheck(9, -1);

		ContentDetailPageSunnxt cdp =new ContentDetailPageSunnxt(driver);
		cdp.getContentName();
		cdp.clickPlayButton();

		test.pass("Playback successful in Shorts Section");

		
	}

}
