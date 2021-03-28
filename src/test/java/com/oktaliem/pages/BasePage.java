package com.oktaliem.pages;

import com.github.javafaker.Faker;
import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.oktaliem.testsuite.TestNG.Preparation.deviceInfo;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

/**
 * @Author Okta Liem
 */
public class BasePage {
    AppiumDriver driver;
    Faker testData = new Faker();

    @AndroidFindBy(id = "com.experitest.eribank:id/nameTextField")
    @iOSXCUITFindBy(accessibility = "nameTextField")
    protected WebElement name;

    @AndroidFindBy(id = "com.experitest.eribank:id/countryTextField")
    @iOSXCUITFindBy(accessibility = "countryTextField")
    protected WebElement ctry;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.TextView")
    protected List<WebElement> countryList;

    @AndroidFindBy(id = "com.experitest.eribank:id/sendPaymentButton")
    @iOSXCUITFindBy(accessibility = "Send Payment")
    protected WebElement sendPayBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/cancelButton")
    @iOSXCUITFindBy(accessibility = "Cancel")
    protected WebElement cancelBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/countryButton")
    @iOSXCUITFindBy(accessibility = "Select")
    protected WebElement selectCountryBtn;

    String country = "France";

    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] getScreenShot(AppiumDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Step
    public void waitUntilElementClickAble(WebElement el, int second) {
        WebDriverWait wait = new WebDriverWait(driver, second);
        wait.until(ExpectedConditions.elementToBeClickable(el));
    }

    @Step
    protected void waitAndFindElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step
    public void staticWait(final long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (final InterruptedException e) {
        }
    }


    @Step
    public void scroll(MobileElement element, int times) {
        int scroll = times;
        for (int i = 0; i < scroll; i++) {
            TouchAction swipe = new TouchAction(driver)
                    .press(PointOption.point(622, 1785))
                    .waitAction(waitOptions(ofMillis(1000)))
                    .moveTo(PointOption.point(540, 672))
                    .release();
            swipe.perform();
            if (element.isDisplayed()) {
                break;
            }
        }

    }

    @Step
    public void checkIfTextIsExpected(WebElement element, String expected) {
        Assert.assertEquals(element.getText(), expected);
    }


    @Step
    public void selectCountry(String country) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            int count = countryList.size();
            staticWait(1000);
            for (int i = 1; i < count; i++) {
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                        "android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/" +
                        "android.widget.ListView/android.widget.TextView[" + i + "]"));
                if (element.getText().equals(country)) {
                    element.click();
                    break;
                }
            }
        } else {
            WebElement element = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + country + "\"]"));
            waitAndFindElement(element);
            element.click();
        }

    }

    @Nullable
    public String getWebContext(AppiumDriver driver) {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                return context;
            }
        }
        return null;
    }
}
