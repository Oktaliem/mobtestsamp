package com.oktaliem.pagesobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
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

}
