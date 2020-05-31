package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

/**
 * @Author Okta Liem
 */
public class EriBankBrowserPage extends AdvanceActions{
    public EriBankBrowserPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/button_once")
    protected WebElement justOneBtn;

    @Step
    public EriBankBrowserPage openWebBrowser() {
        EriBankBrowserBtn.click();
        getScreenShot(driver);
        justOneBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public EriBankBrowserPage navigateToExperitestSite() {
        staticWait(5000);
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
//            https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md
//            https://appium.io/docs/en/writing-running-appium/web/chromedriver/index.html
            System.out.println(contextName);
        }
        driver.context((String) contextNames.toArray()[1]);
        driver.getCurrentUrl().equals("https://experitest.com/");
        getScreenShot(driver);
        return this;
    }
}
