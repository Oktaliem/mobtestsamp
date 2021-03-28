package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/**
 * @Author Okta Liem
 */
public class MenuPage extends BasePage {

    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/makePaymentButton")
    @iOSXCUITFindBy(accessibility = "Make Payment")
    protected WebElement makePayBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/mortageRequestButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Mortgage Request\"]")
    protected WebElement mortgageBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/expenseReportButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Expense Report\"]\n")
    protected WebElement expenseBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/AdvancedActionsButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Advanced Actions\"]")
    protected WebElement advanceBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/logoutButton")
    @iOSXCUITFindBy(id = "Logout")
    protected WebElement logoutBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/" +
            "android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"99.00$\"]")
    protected WebElement balanceStatus;

    @Step
    public MenuPage goToMakePaymentForm() {
        makePayBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public MenuPage goToMortgageRequestForm() {
        mortgageBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public MenuPage goToExpenseReportForm() {
        expenseBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public MenuPage goToAdvanceActionsForm() {
        advanceBtn.click();
        getScreenShot(driver);
        return this;
    }

    @Step
    public MenuPage logOut() {
        logoutBtn.click();
        getScreenShot(driver);
        return this;
    }

}
