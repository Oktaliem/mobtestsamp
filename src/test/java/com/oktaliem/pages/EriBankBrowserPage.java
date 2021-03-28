package com.oktaliem.pages;

import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Diketahui;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Set;

import static com.oktaliem.testsuite.TestNG.Preparation.deviceInfo;

/**
 * @Author Okta Liem
 */
public class EriBankBrowserPage extends AdvanceActions {
    public EriBankBrowserPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "android:id/button_once")
    protected WebElement justOneBtn;

    @iOSXCUITFindBy(accessibility = "urlText")
    protected WebElement url;

    @Step
    public EriBankBrowserPage openWebBrowser() {
        eriBankBrowserBtn.click();
        getScreenShot(driver);
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            justOneBtn.click();
        }
        getScreenShot(driver);
        return this;
    }

    @Step
    public EriBankBrowserPage navigateToExperitestSite() {
        staticWait(5000);
        String expectedUrl = "https://www.experitest.com";
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            Set<String> contextNames = driver.getContextHandles();
            for (String contextName : contextNames) {
//            https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md
//            https://appium.io/docs/en/writing-running-appium/web/chromedriver/index.html
                System.out.println(contextName);
            }
            driver.context((String) contextNames.toArray()[1]);

            System.out.println(driver.getPageSource());
            driver.getCurrentUrl().equals(expectedUrl);
        } else {
            Assert.assertEquals(url.getText(), expectedUrl);
        }
        getScreenShot(driver);
        return this;
    }
}
