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

public class TC_ContentPlaybackMusicVideos extends baseFunction {
	
    private static final Logger logger = Log.getLogger(TC_ContentPlaybackMusicVideos.class);

	
	@BeforeMethod
	public void before() {
setupDriver();
launchSunnxt();
	}
	
	@Test
	public void contentPlayBackCheckTest() throws InterruptedException {
		
		logger.info("Starting playback testing for Music Videos Section");
		test = ExtentReportManager.createTest("Music Videos Section Playback");
		
contentLanguageSelection cls=new contentLanguageSelection(driver);
cls.clickLanguage("tamil");
cls.clickLanguage("telugu");
cls.clickLanguage("malayalam");
cls.clickDoneButton();

HomepageSunnxt hp=new HomepageSunnxt(driver);
implicitWait(10);
hp.clickSignIn();
LoginPageSunnxt lp =new LoginPageSunnxt(driver);
lp.userDetail("9841595069", "123456");

TopMenuSunnxt tm=new TopMenuSunnxt(driver);
tm.redirectionMusicVideos();

hp.contentPlayBackCheck(-1, -1);

ContentDetailPageSunnxt cdp=new ContentDetailPageSunnxt(driver);
cdp.clickPlayButton();

test.pass("Playback successful in Music Videos Section");
	}

}
