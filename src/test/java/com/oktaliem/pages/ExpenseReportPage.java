package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * @Author Okta Liem
 */
public class ExpenseReportPage extends BasePage {
    public ExpenseReportPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/addButton")
    protected WebElement addBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[1]")
    protected WebElement expense2;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[2]")
    protected WebElement detail2;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android" +
            ".widget.TextView[3]")
    protected WebElement info2;


    @Step
    public void createExpenseReport() {
        addBtn.click();
        getScreenShot(driver);
    }

    @Step
    public void expenseReportCreatedSuccessfully() {
        checkIfTextIsExpected(expense2,"Expense : 2");
        checkIfTextIsExpected(detail2,"Detail goes here");
        checkIfTextIsExpected(info2,"Type goes here");
        getScreenShot(driver);
    }
}
