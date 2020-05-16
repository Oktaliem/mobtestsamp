package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
/**
 * @Author Okta Liem
 */
public class ScanCheckPage extends AdvanceActions{

    public ScanCheckPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/OpenCameraButton")
    protected WebElement openCameraBtn;

    @AndroidFindBy(accessibility = "Shutter") //com.android.camera2:id/shutter_button
    protected WebElement shutterBtn;

    @AndroidFindBy(accessibility = "Done") //com.android.camera2:id/done_button
    protected WebElement doneBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/CheckImage")
    protected WebElement photoResult;

    @Step
    public void takeAPhoto() {
        scanCheckBtn.click();
        getScreenShot(driver);
        openCameraBtn.click();
        getScreenShot(driver);
        shutterBtn.click();
        getScreenShot(driver);
        doneBtn.click();
        getScreenShot(driver);
    }

    @Step
    public void ableToTakePhotoSuccessfully() {
        Assert.assertTrue(photoResult.isDisplayed());
    }
}
