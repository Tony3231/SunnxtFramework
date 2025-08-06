package sunnxtPages;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class LiveTVPageSunnxt extends BasePage {

    private static final Logger logger = LogManager.getLogger(LiveTVPageSunnxt.class);

	
	public LiveTVPageSunnxt(WebDriver driver) {
		super(driver);
	}

	@FindBy (xpath = "//div[@class='livetv_tabs_button__t7aG5']/button")
	private List<WebElement> liveTVTabs;

	@FindBy (id = "liveTVChannelsList")
	private List<WebElement> channelLists;

	@FindBy (id = "liveTVChannelsList")
	private WebElement channelList;

	@FindBy (className = "livetv_livetv_channels_titles__qLE0j")
	private List<WebElement> channelName;


	public void contentPlay(int LanguageIndex, int channelIndex) {

		List<String> languageCollections=new ArrayList<String>();

		for(WebElement lang:liveTVTabs) {
			languageCollections.add(lang.getText());
		}

		if(LanguageIndex ==-1 && !liveTVTabs.isEmpty()) {
			int livetv = liveTVTabs.size();
			LanguageIndex = new Random().nextInt(livetv);
		}

		String specificLanguage = liveTVTabs.get(LanguageIndex).getText();

		if(languageCollections.contains(specificLanguage)) {
			WebElement targetlanguage = liveTVTabs.get(LanguageIndex);
			targetlanguage.click();
			System.out.println("Targeted carousel name: " +liveTVTabs.get(LanguageIndex).getText());
			logger.info("Targeted carousel name: {}", liveTVTabs.get(LanguageIndex).getText());
		}else {
            logger.warn("Provided Language isn't listed here: {}", liveTVTabs.get(LanguageIndex).getText());

		}

		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTop+=300;", channelList);

		List<String> channelCollections = new ArrayList<String>();

		for(WebElement channel:channelName) {
			channelCollections.add(channel.getText());
		}

		if(channelIndex== -1 && channelName.size()>0) {
			channelIndex = new Random().nextInt(channelName.size());
		}
		
		if (channelIndex >= channelName.size()) {
            logger.error("No content at given index: {}", channelIndex);			
		}
		
		String specificChannel = channelName.get(channelIndex).getText();

		if(channelCollections.contains(specificChannel)) {
			WebElement targetChannel = channelName.get(channelIndex);
			//scrollIntoView(targetChannel);
			targetChannel.click();
            logger.info("Targeted channel name: {}", specificChannel);
		}else {
			logger.warn("Provided Channel isn't listed here: {}", specificChannel);		}
		
		
	}
}

