package com.oktaliem.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MenuPage extends BasePage {

    public MenuPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "com.experitest.eribank:id/makePaymentButton")
    protected WebElement makePayBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/mortageRequestButton")
    protected WebElement mortgageBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/expenseReportButton")
    protected WebElement expenseBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/AdvancedActionsButton")
    protected WebElement advanceBtn;

    @AndroidFindBy(id = "com.experitest.eribank:id/logoutButton")
    protected WebElement logoutBtn;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
            "FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/" +
            "android.widget.LinearLayout/android.webkit.WebView/android.webkit.WebView/android.view.View")
    protected WebElement balanceStatus;

    @Step
    @net.thucydides.core.annotations.Step
    public void goToMakePaymentForm() {
        makePayBtn.click();
        getScreenShot(driver);
    }
}
