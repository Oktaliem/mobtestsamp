package com.oktaliem.pages;

import com.testinium.deviceinformation.device.DeviceType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.oktaliem.testsuite.TestNG.Preparation.deviceInfo;

/**
 * @Author Okta Liem
 */
public class ExpenseReportPage extends BasePage {
    public ExpenseReportPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/addButton")
    @iOSXCUITFindBy(accessibility = "Add")
    protected WebElement addBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[1]")
    @iOSXCUITFindBy(accessibility = "Expense 2")
    protected WebElement expense2;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Detail goes here\"])[3]")
    protected WebElement detail2;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[3]")
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeStaticText[@name=\"Type goes here\"])[3]")
    protected WebElement info2;


    @Step
    public ExpenseReportPage createExpenseReport() {
        addBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public ExpenseReportPage expenseReportCreatedSuccessfully() {
        if (deviceInfo.equals(DeviceType.ANDROID)) {
            checkIfTextIsExpected(expense2, "Expense : 2");
        } else {
            checkIfTextIsExpected(expense2, "Expense 2");
        }
        checkIfTextIsExpected(detail2, "Detail goes here");
        checkIfTextIsExpected(info2, "Type goes here");
        getScreenShot(driver);
        return this;
    }
}
