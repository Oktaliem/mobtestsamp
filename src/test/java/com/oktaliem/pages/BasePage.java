package com.oktaliem.pages;

import com.github.javafaker.Faker;
import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.time.Duration;
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


    public void swipeElementIOS(MobileElement el, String dir) {
        System.out.println("swipeElementIOS(): dir: '" + dir + "'"); // always log your actions

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 500; // ms

        // init screen variables
        Dimension dims = driver.manage().window().getSize();
        Rectangle rect = el.getRect();

        // check element overlaps screen
        if (rect.x >= dims.width || rect.x + rect.width <= 0
                || rect.y >= dims.height || rect.y + rect.height <= 0) {
            throw new IllegalArgumentException("swipeElementIOS(): Element outside screen");
        }

        // init borders per your app screen
        // or make them configurable with function variables
        int leftBorder, rightBorder, upBorder, downBorder;
        leftBorder = 0;
        rightBorder = 0;
        upBorder = 0;
        downBorder = 0;

        // find rect that overlap screen
        if (rect.x < 0) {
            rect.width = rect.width + rect.x;
            rect.x = 0;
        }
        if (rect.y < 0) {
            rect.height = rect.height + rect.y;
            rect.y = 0;
        }
        if (rect.width > dims.width)
            rect.width = dims.width;
        if (rect.height > dims.height)
            rect.height = dims.height;

        PointOption pointOptionStart, pointOptionEnd;
        switch (dir) {
            case "DOWN": // from up to down
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                break;
            case "UP": // from down to up
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                break;
            case "LEFT": // from right to left
                pointOptionStart = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                break;
            case "RIGHT": // from left to right
                pointOptionStart = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementIOS(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementIOS(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
        }
    }
}
