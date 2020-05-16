package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MakePaymentPage extends BasePage {

    public MakePaymentPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

    }
    MenuPage menuPage;

    @AndroidFindBy(id = "com.experitest.eribank:id/phoneTextField")
    protected WebElement phoneNum;

    @AndroidFindBy(id = "com.experitest.eribank:id/amountTextField")
    protected WebElement amount;

    @Step
    public void inputPaymentInfo() {
        phoneNum.sendKeys("+2123456");
        name.sendKeys("Okta Liem");
        amount.sendKeys("1");

        selectCountry("France");
        getScreenShot(driver);
    }

    @Step
    public void makePayment() {
        Assert.assertEquals(ctry.getText(), country);
        sendPayBtn.click();
        getScreenShot(driver);
        clickConfirmationBtn("Yes");
        getScreenShot(driver);
    }

    @Step
    public void checkIfPaymentIfSuccessful() {
        staticWait(1000);
        menuPage = new MenuPage(driver);
        Assert.assertEquals(menuPage.balanceStatus.getText(), "Your balance is: 99.00$");
        getScreenShot(driver);
    }

    @Step
    public void clickConfirmationBtn(String status) {
        if (status.equals("Yes")) {
            driver.findElement(By.id("android:id/button1")).click();
        } else {
            driver.findElement(By.id("android:id/button2")).click();
        }
        getScreenShot(driver);
    }

    @Step
    public void cancelMakePaymentForm() {
        Assert.assertEquals(ctry.getText(), country);
        cancelBtn.click();
        getScreenShot(driver);
    }

    @Step
    public void cancelMakePayment() {
        Assert.assertEquals(ctry.getText(), country);
        sendPayBtn.click();
        staticWait(1000);
        getScreenShot(driver);
        clickConfirmationBtn("No");
        getScreenShot(driver);
    }

    @Step
    public void checkIfCancelPaymentFormIsSuccessful() {
        staticWait(1000);
        menuPage = new MenuPage(driver);
        Assert.assertEquals(menuPage.balanceStatus.getText(), "Your balance is: 100.00$");
        getScreenShot(driver);
    }

    @Step
    @net.thucydides.core.annotations.Step
    public void checkIfCancelPaymentIsSuccessful() {
        Assert.assertEquals(ctry.getText(), country);
        getScreenShot(driver);
    }

}
