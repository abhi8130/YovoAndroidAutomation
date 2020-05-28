package com.automation.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreatePostPage extends BasePOMPage {

	// object repository for mvf
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Choose a contest']")
	private AndroidElement chooseContestTxt;

	@AndroidFindBy(id = "challenge_name")
	private AndroidElement challengeName;

	@AndroidFindBy(id = "com.getyovo:id/title")
	private AndroidElement contesttitle;

	@AndroidFindBy(id = "com.getyovo:id/close_button")
	private AndroidElement melvinIntroCloseBtn;

	@AndroidFindBy(id = "com.getyovo:id/challenge_name")
	private AndroidElement challengeNameTxt;

	@AndroidFindBy(id = "com.getyovo:id/mvfIntroSkipButton")
	private AndroidElement mvfIntroSkipButton;

	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Start Recording']")
	private AndroidElement startRecordingBtn;

	@AndroidFindBy(id = "com.getyovo:id/audioTrack")
	private AndroidElement audioTrack;

	@AndroidFindBy(xpath = "//androidx.appcompat.app.ActionBar.b[@content-desc=\"Discover\"]")
	private AndroidElement discoverTxt;

	@AndroidFindBy(id = "com.getyovo:id/iv_audio_checked")
	private AndroidElement audioChecked;

	@AndroidFindBy(id = "com.getyovo:id/text_song_name")
	private AndroidElement songNameTxt;

	@AndroidFindBy(id = "com.getyovo:id/loader_line")
	private AndroidElement loaderLine;

	@AndroidFindBy(id = "com.getyovo:id/self")
	private AndroidElement recordBtn;

	@AndroidFindBy(id = "com.getyovo:id/timer")
	private AndroidElement recordTimer;

	@AndroidFindBy(id = "com.getyovo:id/next")
	private AndroidElement doneTxt;

	@AndroidFindBy(id = "com.getyovo:id/post_button")
	private AndroidElement postBtn;

	@AndroidFindBy(id = "com.getyovo:id/mvfVideoTitle")
	private AndroidElement mvfVideoTitle;

	// object repository for bvf
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Let's Record your first video]")
	private AndroidElement firstInstructionsBvfTxt;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Let's Record your second video]")
	private AndroidElement secondInstructionsBvfTxt;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Let's Record your third video]")
	private AndroidElement thirdInstructionsBvfTxt;
	
	@AndroidFindBy(id="com.getyovo:id/tag_view")
	private AndroidElement tagView;
	
	@AndroidFindBy(xpath = "//*[@class='android.widget.TextView' and @text='Sounds']")
	private AndroidElement bvfSoundsTxt;

	@AndroidFindBy(id="com.getyovo:id/ques_text")
	private AndroidElement addDescriptionBvfTxt;
	
	@AndroidFindBy(id="com.getyovo:id/first_option")
	private AndroidElement firstOptionBvf;
	
	@AndroidFindBy(id="com.getyovo:id/second_option")
	private AndroidElement secondOptionBvf;
	
	@AndroidFindBy(id="com.getyovo:id/done_editing")
	private AndroidElement doneEditingBtn;
	

	// initialize objects
	public CreatePostPage(AndroidDriver<MobileElement> driver) {
		super(driver);
	}

	//mvf actions
	public boolean verifyChooseContestTxt() {
		try {
			return chooseContestTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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

	public void clickSoundsTxt() {
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

	public boolean isMelvinIntroCloseBtnDisplayed() {
		try {
			return melvinIntroCloseBtn.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isMvfIntroSkipBtnDisplayed() {
		 try {
			return mvfIntroSkipButton.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void clickMvfIntroSkipBtn() {
		mvfIntroSkipButton.click();
	}

	//bvf actions
	public boolean isfirstInstructionsBvfTxtDisplayed() {
		 try {
			return firstInstructionsBvfTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isSecondInstructionsBvfTxtDisplayed() {
		 try {
			return secondInstructionsBvfTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isThirdInstructionsBvfTxtDisplayed() {
		 try {
			return thirdInstructionsBvfTxt.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isTagViewDisplayed() {
		 try {
			return tagView.isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void clickTagView() {
		tagView.click();
	}
	
	public void typeInTagView(String text) {
		tagView.sendKeys(text);
	}
	
	public void clickBvfSoundsTxt() {
		bvfSoundsTxt.click();
	}
	
	public void typeDescriptionBvfTxt(String text) {
		addDescriptionBvfTxt.sendKeys(text);
	}
	
	public void typeFirstOptionBvfTxt(String text) {
		firstOptionBvf.sendKeys("text");
	}
	
	public void typeSecondBvfTxt(String text) {
		secondOptionBvf.sendKeys("text");
	}
	
	public void clickDoneEditingBtn() {
		doneEditingBtn.click();
	}
	
	
}
