package com.oktaliem.pagesobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * @Author Okta Liem
 */
public class LoginPage {

    AppiumDriver driver;

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
    public void loginToApp() {
        userName.sendKeys("company");
        password.sendKeys("company");
        loginBtn.click();
    }
}
