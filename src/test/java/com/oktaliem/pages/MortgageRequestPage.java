package com.oktaliem.pages;

import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static com.oktaliem.testsuite.TestNG.Preparation.deviceInfo;

/**
 * @Author Okta Liem
 */
public class MortgageRequestPage extends BasePage {

    public MortgageRequestPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    MenuPage menuPage;

    @AndroidFindBy(id = "com.experitest.eribank:id/lastNameTextField")
    @iOSXCUITFindBy(accessibility = "lastNameTextField")
    protected WebElement lastName;

    @AndroidFindBy(id = "com.experitest.eribank:id/ageTextField")
    @iOSXCUITFindBy(accessibility = "ageTextField")
    protected WebElement age;

    @AndroidFindBy(id = "com.experitest.eribank:id/addressOneTextField")
    @iOSXCUITFindBy(accessibility = "addressOneTextField")
    protected WebElement address1;

    @AndroidFindBy(id = "com.experitest.eribank:id/addressTwoTextField")
    @iOSXCUITFindBy(accessibility = "addressTwoTextField")
    protected WebElement address2;

    @AndroidFindBy(id = "com.experitest.eribank:id/nextButton")
    @iOSXCUITFindBy(accessibility = "Next")
    protected WebElement nextBtn;

    @AndroidFindBy(xpath = "\t\n" +
            "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout[2]/android." +
            "widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[1]" +
            "/android.widget.CheckedTextView")
    protected List<WebElement> typeOfLoan;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget." +
            "LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android." +
            "widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[2]/android.widget." +
            "CheckedTextView")
    protected List<WebElement> numOfYears;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android." +
            "widget.ListView[2]/android.widget.CheckedTextView")
    protected List<WebElement> typeOfOccupation;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android." +
            "widget.ListView[3]/android.widget.CheckedTextView")
    protected List<WebElement> yearlyIncome;

    @AndroidFindBy(id = "com.experitest.eribank:id/saveButton")
    @iOSXCUITFindBy(accessibility = "Save")
    protected MobileElement saveBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/backButton")
    @iOSXCUITFindBy(accessibility = "Back")
    protected WebElement backBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/typeOfOccupationTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Type Of Occupation\"]")
    protected MobileElement typeofOccText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Business\"]")
    protected MobileElement businessText;

    @iOSXCUITFindBy(accessibility = "firstNameTextField")
    protected WebElement firstName;


    @Step
    public MortgageRequestPage inputRequestForm() {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            name.sendKeys(testData.name().firstName());
        } else {
            firstName.sendKeys(testData.name().firstName());
        }
        lastName.sendKeys(testData.name().lastName());
        age.sendKeys("45");
        address1.sendKeys(testData.address().fullAddress());
        address2.sendKeys(testData.address().fullAddress());
        selectCountryBtn.click();
        selectCountry("France");
        getScreenShot(driver); //1
        nextBtn.click();
        selectTypeOfLoan("Car");
        getScreenShot(driver); //2
        selectNumberOfYears("15");
        getScreenShot(driver); //3
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            scroll(typeofOccText, 3);
        }
        getScreenShot(driver); //4
        selectTypeOfOccupation("Business");
        staticWait(2000);
        getScreenShot(driver); //5
        selectYearlyIncome("500,000");
        staticWait(2000);
        getScreenShot(driver); //6
        scroll(saveBtn, 3);
        saveBtn.click();
        getScreenShot(driver); //7
        return this;
    }

    @Step
    private MortgageRequestPage selectTypeOfLoan(String loadType) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            int count = typeOfLoan.size();
            staticWait(1000);
            for (int i = 1; i < count; i++) {
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget." +
                        "LinearLayout/android.widget." +
                        "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget." +
                        "LinearLayout/android.widget.ListView[1]/android.widget.CheckedTextView[" + i + "]"));
                if (element.getText().equals(loadType)) {
                    element.click();
                    break;
                }
            }
        } else {
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + loadType + "\"]")).click();
        }
        return this;
    }

    @Step

    private MortgageRequestPage selectNumberOfYears(String years) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            int count = numOfYears.size();
            staticWait(1000);
            for (int i = 1; i < count; i++) {
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget." +
                        "LinearLayout/android.widget.FrameLayout[2]/android.widget.LinearLayout/android." +
                        "widget.ScrollView/android.widget.LinearLayout/android.widget.ListView[2]/android.widget." +
                        "CheckedTextView[" + i + "]"));
                if (element.getText().equals(years)) {
                    element.click();
                    break;
                }
            }
        } else {
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + years + "\"]")).click();
        }
        return this;
    }

    @Step
    private MortgageRequestPage selectTypeOfOccupation(String occupation) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            int count = typeOfOccupation.size();
            staticWait(1000);
            for (int i = 1; i < count; i++) {
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget." +
                        "LinearLayout/android.widget." +
                        "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget." +
                        "LinearLayout/android." +
                        "widget.ListView[2]/android.widget.CheckedTextView[" + i + "]"));
                if (element.getText().equals(occupation)) {
                    element.click();
                    break;
                }
            }
        } else {
            driver.findElement(By.id(occupation)).click();
        }
        return this;
    }

    @Step
    private MortgageRequestPage selectYearlyIncome(String income) {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            int count = yearlyIncome.size();
            staticWait(1000);
            for (int i = 1; i < count; i++) {
                WebElement element = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget." +
                        "LinearLayout/android.widget." +
                        "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget." +
                        "LinearLayout/android." +
                        "widget.ListView[3]/android.widget.CheckedTextView[" + i + "]"));
                if (element.getText().equals(income)) {
                    element.click();
                    break;
                }
            }
        } else {
            driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"" + income + "\"]")).click();
        }
        return this;
    }

    @Step
    public MortgageRequestPage checkIfRequestIfSuccessful() {
        staticWait(1000);
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            menuPage = new MenuPage(driver);
            Assert.assertEquals(menuPage.balanceStatus.getText(), "Your balance is: 100.00$");
        } else {
            Assert.assertTrue(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"100.00$\"]")).isDisplayed());
        }
        getScreenShot(driver);
        return this;
    }
}
