package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;

public class HomePage extends BasePOMPage {

	@AndroidFindBy(id = "continue_button")
	private AndroidElement continueBtn;

	@AndroidFindBy(id = "user_name")
	private AndroidElement userProfile;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Comment']")
	private AndroidElement commentTxt;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Top 80']")
	private AndroidElement top50Txt;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Share']")
	private AndroidElement shareTxt;

	@AndroidFindBy(id="com.getyovo:id/back_button")
	private AndroidElement backBtn;
	
	@AndroidFindBy(id = "send_coin_text")
	private AndroidElement sendCoinTxt;

	@AndroidFindBy(id = "challenge_name")
	private AndroidElement challengeName;

	@AndroidFindBy(id = "options_view")
	private AndroidElement optionsView;

	@AndroidFindBy(id = "layout1")
	private AndroidElement layout1_HomeTab;

	@AndroidFindBy(id = "layout2")
	private AndroidElement layout2_SearchTab;

	@AndroidFindBy(id = "layout3")
	private AndroidElement layout3_createPost;

	@AndroidFindBy(id = "layout4")
	private AndroidElement layout4_NotificationTab;

	@AndroidFindBy(id = "layout5")
	private AndroidElement layout5_ProfileTab;
	
	@AndroidFindBy(id = "com.getyovo:id/cross")
	private AndroidElement crossIcon;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView'and @text='Sign Up/Log In")
	private AndroidElement signUpLogInTxt;
	
	@AndroidFindBy(accessibility = "097735 44748")
	private AndroidElement cellNumber;
	
	@AndroidFindBy(id="com.getyovo:id/number_header")
	private AndroidElement enterPhoneNumber;
	
	@AndroidFindBy(id="com.google.android.gms:id/cancel")
	private AndroidElement noneTxt;
	
	@AndroidFindBy(id="com.getyovo:id/continue_button")
	private AndroidElement continueButton;
	
	@AndroidFindBy(id="com.getyovo:id/show_number")
	private AndroidElement showNumber;
	
	@AndroidFindBy(id="com.getyovo:id/otp_view")
	private AndroidElement otpView;
	
	@AndroidFindBy(id="com.getyovo:id/continue_with_otp")
	private AndroidElement continueWithOtp;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView' and @text='Choose a contest']")
	private AndroidElement chooseContestTxt;
	
	@AndroidFindBy(id="com.getyovo:id/title")
	private AndroidElement contesttitle;
	
	@AndroidFindBy(id="com.getyovo:id/close_button")
	private AndroidElement melvinIntroCloseBtn;
	
	@AndroidFindBy(id="com.getyovo:id/challenge_name")
	private AndroidElement challengeNameTxt;
	
	@AndroidFindBy(id="com.getyovo:id/mvfIntroSkipButton")
	private AndroidElement mvfIntroSkipButton;
	
	@AndroidFindBy(xpath="//*[@class='android.widget.TextView' and @text='Start Recording']")
	private AndroidElement startRecordingBtn;
	
	@AndroidFindBy(id="com.getyovo:id/audioTrack")
	private AndroidElement audioTrack;
	
	@AndroidFindBy(xpath="//androidx.appcompat.app.ActionBar.b[@content-desc=\"Discover\"]")
	private AndroidElement discoverTxt;
	
	@AndroidFindBy(id="com.getyovo:id/iv_audio_checked")
	private AndroidElement audioChecked;
	
	@AndroidFindBy(id="com.getyovo:id/text_song_name")
	private AndroidElement songNameTxt;
	
	@AndroidFindBy(id="com.getyovo:id/loader_line")
	private AndroidElement loaderLine;
	
	@AndroidFindBy(id="com.getyovo:id/self")
	private AndroidElement recordBtn;
	
	@AndroidFindBy(id="com.getyovo:id/timer")
	private AndroidElement recordTimer;

	@AndroidFindBy(id="com.getyovo:id/next")
	private AndroidElement doneTxt;
	
	@AndroidFindBy(id="com.getyovo:id/post_button")
	private AndroidElement postBtn;
	
	@AndroidFindBy(id="com.getyovo:id/mvfVideoTitle")
	private AndroidElement mvfVideoTitle;
	
	
	public HomePage(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	@Step("Clicks on continue button after splashscreen")
	public void clickContinueBtnAfterSplashScreen() {
		continueBtn.click();
	}

	@Step("Getting username from homepage")
	public String getUserNamefromHomePage() {
		return userProfile.getText();
	}

	@Step("Validate Top50 text is displayed")
	public boolean validateTop50Txt() {
		return top50Txt.isDisplayed();
	}

	@Step("Clicks on username & redirects to the userprofile page")
	public UserProfilePage clickUserName() {
		userProfile.click();
		return new UserProfilePage(driver);
	}

	@Step("Click on Back button")
	public void clickBackBtn(){
		backBtn.click();
	}
	
	@Step("Click on profile from the navigation bar")
	public void clickOnProfile(){
		layout5_ProfileTab.click();
	}
	
	@Step("Click on create post icon from the navigation bar")
	public CreatePostPage  clickCreatePostIcon() {
		layout3_createPost.click();
		return new CreatePostPage(driver);
	}
	
	public void clickOnEnterYourMobileNumber(){
		enterPhoneNumber.click();
	}
	
	public void clickCellNumber(){
		cellNumber.click();
	}
	
	public void clickNoneTxt(){
		noneTxt.click();
	}

	public void clickContinueBtn() {
		continueButton.click();
	}
	
	public void typeOTP(String otp) {
		otpView.sendKeys(otp);
	}
	
	public void continueWithOtpBtn() {
		continueWithOtp.click();
	}
	
	
	public boolean verifyChooseContestTxt() {
		return chooseContestTxt.isDisplayed();
	}
	
	public String getChallengeName() {
		return challengeName.getText();
	}
	
	public void clickStartRecoridngBtn() {
		startRecordingBtn.click();
	}
	
	public boolean verifySoundsTxt() {
		return audioTrack.isDisplayed();
	}
	public void clickSoundsTxt(){
		audioTrack.click();
	}
	
	public boolean verifyDiscoverTxt() {
		return discoverTxt.isDisplayed();
	}
	
	public void clickAudioChecked() {
		audioChecked.click();
	}
	
	public void clickRecordBtn() {
		recordBtn.click();
	}
	
	public boolean verifyRecordTimerIsDisplayed() {
		return recordTimer.isDisplayed();
	}
	public boolean verifyLoaderLine() {
		return loaderLine.isDisplayed();
	}
	
	public void clickDoneTxt() {
		doneTxt.click();
	}
	
	public boolean verifyMvfVideoTitle() {
		return mvfVideoTitle.isDisplayed();
	}
	
	public void clickPostBtn() {
		postBtn.click();
	}
	
	public void clickMelvinIntroCloseBtn() {
		melvinIntroCloseBtn.click();
	}
	
	public boolean verifyMelvinIntroCloseBtn() {
		return melvinIntroCloseBtn.isDisplayed();
	}
	
	
	public boolean verifyMvfIntroSkipBtn() {
		return mvfIntroSkipButton.isDisplayed();
	}
	
	public void clickMvfIntroSkipBtn() {
		mvfIntroSkipButton.click();
	}

	public boolean isMelvinIntroCloseBtnDisplayed() {
		try {
			return melvinIntroCloseBtn.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
