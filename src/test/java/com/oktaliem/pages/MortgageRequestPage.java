package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
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
    protected WebElement lastName;

    @AndroidFindBy(id = "com.experitest.eribank:id/ageTextField")
    protected WebElement age;

    @AndroidFindBy(id = "com.experitest.eribank:id/addressOneTextField")
    protected WebElement address1;

    @AndroidFindBy(id = "com.experitest.eribank:id/addressTwoTextField")
    protected WebElement address2;

    @AndroidFindBy(id = "com.experitest.eribank:id/nextButton")
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
    protected MobileElement saveBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/backButton")
    protected WebElement backBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/typeOfOccupationTextView")
    protected MobileElement typeofOccText;

    @Step
    public MortgageRequestPage inputRequestForm() {
        name.sendKeys(testData.name().firstName());
        lastName.sendKeys(testData.name().lastName());
        age.sendKeys("45");
        address1.sendKeys(testData.address().fullAddress());
        address2.sendKeys(testData.address().fullAddress());
        selectCountryBtn.click();
        selectCountry("France");
        getScreenShot(driver);
        nextBtn.click();
        selectTypeOfLoan("Car");
        getScreenShot(driver);
        selectNumberOfYears("15");
        getScreenShot(driver);
        scroll(typeofOccText, 3);
        getScreenShot(driver);
        selectTypeOfOccupation("Business");
        getScreenShot(driver);
        selectYearlyIncome("500,000");
        scroll(saveBtn, 3);
        saveBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    private MortgageRequestPage selectTypeOfLoan(String loadType) {
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
        return this;
    }

    @Step
    private MortgageRequestPage selectNumberOfYears(String years) {
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
        return this;
    }

    @Step
    private MortgageRequestPage selectTypeOfOccupation(String occupation) {
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
        return this;
    }

    @Step
    private MortgageRequestPage selectYearlyIncome(String income) {
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
        return this;
    }

    @Step
    public MortgageRequestPage checkIfRequestIfSuccessful() {
        staticWait(1000);
        menuPage = new MenuPage(driver);
        Assert.assertEquals(menuPage.balanceStatus.getText(), "Your balance is: 100.00$");
        getScreenShot(driver);
        return this;
    }
}
