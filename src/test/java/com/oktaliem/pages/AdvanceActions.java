package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * @Author Okta Liem
 */
public class AdvanceActions extends BasePage {
    public AdvanceActions(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/ScanCheckButton")
    protected WebElement scanCheckBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/RecordAudioutton")
    protected WebElement sendVoidRequestBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/OpenBrowserButton")
    protected WebElement EriBankBrowserBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/BackButton")
    protected WebElement backBtn;

}
