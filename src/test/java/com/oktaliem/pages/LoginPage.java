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
        userName.sendKeys("company");
        password.sendKeys("company");
        loginBtn.click();
        getScreenShot(driver);
    }

    @Step
    @net.thucydides.core.annotations.Step
    public void landingToMenuPage() {
        menuPage = new MenuPage(driver);
        waitUntilElementClickAble(menuPage.logoutBtn,5);
        Assert.assertEquals(menuPage.logoutBtn.getText(),"Logout");
        getScreenShot(driver);
    }


}
