package com.automation.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.base.BaseClass;
import com.automation.pages.CreatePostPage;
import com.automation.pages.HomePage;
import com.automation.util.TestUtil;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CreatePostPageTest extends BaseClass {

	HomePage homePage;
	CreatePostPage createPostPage;

	public CreatePostPageTest() {
		super();
	}

	@BeforeClass
	@Parameters({ "platformName", "url"})
	public void setUpHomePageClass(String platformName, String url, String udid) throws Exception {
		try {
			BaseClass baseClass = new BaseClass();
			baseClass.initialize_driver(platformName, url);
			createPostPage = new CreatePostPage(driver);
			homePage = new HomePage(driver);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void postingVideoMvfTypeTest() {
		logger = extent.createTest("postingVideoMvfType ");
		log.info("***Executing postingVideoMvfType***");
		if (createPostPage.isMelvinIntroCloseBtnDisplayed() == true)
			createPostPage.clickMelvinIntroCloseBtn();
		else
			System.out.println("MelvinIntroCloseBtn is not displayed");

		log.info("waitForElementToPresenceOfElementLocated - username");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("user_name"));
		log.info("Clicking on create post icon");
		createPostPage = homePage.clickCreatePostIcon();
		boolean flag = createPostPage.verifyChooseContestTxt();
		Assert.assertTrue(flag);
		log.info("click on contest fron the list");
		TestUtil.clickElementFromListByText(By.id("com.getyovo:id/title"), prop.getProperty("contest"));
		log.info("waitForElementToPresenceOfElementLocated - challenge_name");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.getyovo:id/challenge_name"));
		String getChallengeNameTxt = createPostPage.getChallengeName();
		Assert.assertEquals(getChallengeNameTxt, prop.getProperty("contest"));
		log.info("Assettion passed - challenge_name");
		createPostPage.clickStartRecoridngBtn();
		log.info("Clicked on start recording btn");

		if (createPostPage.isMvfIntroSkipBtnDisplayed() == true)
			createPostPage.clickMvfIntroSkipBtn();
		else
			System.out.println("MvfIntroSkipBtn is not displayed");

		// TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.getyovo:id/audioTrack"));
		log.info("clicked on sounds text");
		createPostPage.clickSoundsTxt();
		log.info("select song from list of songs");
		TestUtil.clickElementFromListByText(By.id("com.getyovo:id/text_song_name"), prop.getProperty("songName"));
		createPostPage.clickAudioChecked();
		log.info("click on audiochecked on selected song");
		TestUtil.waitForElementToBeClickable(By.id("com.getyovo:id/self"));
		log.info("click on record button");
		TestUtil.longPressOnElement(driver.findElementById("com.getyovo:id/self"));
		createPostPage.clickDoneTxt();
		log.info("waitForElementToPresenceOfElementLocated - mvfVideoTitle");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.getyovo:id/post_button"));
		log.info("clicking on post button");
		createPostPage.clickPostBtn();
	}

	@Test(priority = 2)
	public void postingVideoBvfTyppeTest() throws InterruptedException {
		logger = extent.createTest("postingZVideoBvfTyppeTest");
		log.info("***Executing postingVideoBvfType***");
		if (createPostPage.isMelvinIntroCloseBtnDisplayed() == true)
			createPostPage.clickMelvinIntroCloseBtn();
		else
			System.out.println("MelvinIntroCloseBtn is not displayed");

		log.info("waitForElementToPresenceOfElementLocated - username");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("user_name"));
		createPostPage = homePage.clickCreatePostIcon();
		log.info("Clicked on create post icon");
		boolean flag = createPostPage.verifyChooseContestTxt();
		Assert.assertTrue(flag);
		log.info("click on contest fron the list");
		TestUtil.clickElementFromListByText(By.id("com.getyovo:id/title"), prop.getProperty("contest"));

		for (int i = 0; i <= 2; i++) {
			log.info("click on sounds text");
			TestUtil.waitForElementToBeClickable(By.xpath("//*[@class='android.widget.TextView' and @text='Sounds']"));
			createPostPage.clickBvfSoundsTxt();
			log.info("select song from the list ");
			TestUtil.waitForElementToBeClickable(By.id("com.getyovo:id/text_song_name"));
			TestUtil.clickElementFromListByText(By.id("com.getyovo:id/text_song_name"), prop.getProperty("songName"));
			TestUtil.waitForElementToBeClickable(By.id("com.getyovo:id/iv_audio_checked"));
			createPostPage.clickAudioChecked();
			log.info("click on audiochecked on selected song");
			TestUtil.waitForElementToBeClickable(By.id("com.getyovo:id/self"));
			log.info("long press on record button");
			TestUtil.longPressOnElement(driver.findElementById("com.getyovo:id/self"));
			log.info("click on done");
			createPostPage.clickDoneTxt();
			TestUtil.waitForElementToBeClickable(By.xpath("//*[@class='android.widget.TextView' and @text='Sounds']"));
			log.info("---------------------------");
		}
		log.info("waitForElementToPresenceOfElementLocated - tag_view");
		TestUtil.waitForElementToPresenceOfElementLocated(By.id("com.getyovo:id/tag_view"));
		// createPostPage.clickTagView();
		log.info("type text in tag_view");
		createPostPage.typeInTagView(prop.getProperty("tagsView"));
		log.info("click on Next Btn");
		createPostPage.clickPostBtn();
		log.info("waitForElementToPresenceOfElementLocated - Preview Btn");
		TestUtil.waitForElementToPresenceOfElementLocated(
				By.xpath("//*[@class='android.widget.TextView' and @text ='Preview']"));
		log.info("type text in addDescription ");
		createPostPage.typeDescriptionBvfTxt(prop.getProperty("addDescription"));
		log.info("type text in option1 ");
		createPostPage.typeFirstOptionBvfTxt(prop.getProperty("option1"));
		log.info("type text in option2 ");
		createPostPage.typeSecondBvfTxt(prop.getProperty("option2"));
		log.info("click on preview btn ");
		createPostPage.clickPostBtn();
		createPostPage.clickPostBtn();
		log.info("clicked on Next Btn");

	}

	@AfterClass
	public void tearDown() throws IOException {
		getDriver().quit();
	}

}
