package com.oktaliem;

import com.oktaliem.pages.*;
import io.appium.java_client.AppiumDriver;

public class User {

    AppiumDriver driver;

    public User(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() { return new LoginPage(driver);}
    public MenuPage menuPage() { return new MenuPage(driver);}
    public MakePaymentPage makePaymentPage(){return new MakePaymentPage(driver);}
    public MortgageRequestPage mortgageRequestPage(){return new MortgageRequestPage(driver);}
    public ExpenseReportPage expenseReportPage(){return new ExpenseReportPage(driver);}
    public AdvanceActions advanceActions(){return new AdvanceActions(driver);}
    public ScanCheckPage scanCheckPage(){return new ScanCheckPage(driver);}
    public EriBankBrowserPage eriBankBrowserPage(){return new EriBankBrowserPage(driver);}
}
