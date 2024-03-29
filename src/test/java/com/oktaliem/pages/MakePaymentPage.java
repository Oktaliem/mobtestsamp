package com.oktaliem.pages;

import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.oktaliem.constants.TestData.*;
import static com.oktaliem.testsuite.TestNG.Preparation.deviceInfo;

/**
 * @Author Okta Liem
 */
public class MakePaymentPage extends BasePage {

    public MakePaymentPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }

    MenuPage menuPage;

    @AndroidFindBy(id = "com.experitest.eribank:id/phoneTextField")
    @iOSXCUITFindBy(accessibility = "phoneTextField")
    protected WebElement phoneNum;

    @AndroidFindBy(id = "com.experitest.eribank:id/amountTextField")
    @iOSXCUITFindBy(accessibility = "amountTextField")
    protected WebElement amount;

    @Step
    public MakePaymentPage inputPaymentInfo() {
        String pm = testData.phoneNumber().cellPhone();
        String nm = testData.phoneNumber().cellPhone();
        waitUntilElementClickAble(phoneNum, 60);
        phoneNum.sendKeys(pm);
        name.sendKeys(nm);
        amount.sendKeys("1");
        selectCountryBtn.click();
        selectCountry(COUNTRY);
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage makePayment() {
        Assert.assertEquals(ctry.getText(), COUNTRY);
        sendPayBtn.click();
        getScreenShot(driver);
        clickConfirmationBtn("Yes");
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage checkIfPaymentIfSuccessful() {
        staticWait(1000);
        menuPage = new MenuPage(driver);
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            Assert.assertEquals(menuPage.balanceStatus.getText(), "Your balance is: 99.00$");
        } else {
            waitAndFindElement(menuPage.balanceStatus);
            menuPage.balanceStatus.isDisplayed();
        }
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage clickConfirmationBtn(String status) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            if (status.equals("Yes")) {
                driver.findElement(By.id("android:id/button1")).click();
            } else {
                driver.findElement(By.id("android:id/button2")).click();
            }
        } else {
            driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"" + status + "\"]")).click();
        }
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage cancelMakePaymentForm() {
        Assert.assertEquals(ctry.getText(), country);
        cancelBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage cancelMakePayment() {
        Assert.assertEquals(ctry.getText(), country);
        sendPayBtn.click();
        staticWait(1000);
        getScreenShot(driver);
        clickConfirmationBtn("No");
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage checkIfCancelPaymentFormIsSuccessful() {
        staticWait(1000);
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            menuPage = new MenuPage(driver);
            Assert.assertEquals(menuPage.balanceStatus.getText(), ORIGINAL_BALANCE);
        } else {
            Assert.assertTrue(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"100.00$\"]")).isDisplayed());
        }
        getScreenShot(driver);
        return this;
    }

    @Step
    public MakePaymentPage checkIfCancelPaymentIsSuccessful() {
        Assert.assertEquals(ctry.getText(), country);
        getScreenShot(driver);
        return this;
    }

}
