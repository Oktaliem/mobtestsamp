package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static com.oktaliem.constants.TestData.PASS;
import static com.oktaliem.constants.TestData.USER_NAME;

/**
 * @Author Okta Liem
 */
public class LoginPage extends BasePage {
    MenuPage menuPage;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/usernameTextField")
    protected WebElement userName;

    @AndroidFindBy(id = "com.experitest.eribank:id/passwordTextField")
    protected WebElement password;

    @AndroidFindBy(id = "com.experitest.eribank:id/loginButton")
    protected WebElement loginBtn;

    @Step
    @net.thucydides.core.annotations.Step
    public void loginToApp() {
        waitUntilElementClickAble(userName, 60);
        userName.sendKeys(USER_NAME);
        password.sendKeys(PASS);
        loginBtn.click();
        getScreenShot(driver);
    }

    @Step
    public void landingToMenuPage() {
        menuPage = new MenuPage(driver);
        waitUntilElementClickAble(menuPage.logoutBtn, 60);
        Assert.assertEquals(menuPage.logoutBtn.getText(), "Logout");
        getScreenShot(driver);
    }


}
