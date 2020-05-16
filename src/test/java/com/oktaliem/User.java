package com.oktaliem;

import com.oktaliem.pages.LoginPage;
import com.oktaliem.pages.MakePaymentPage;
import com.oktaliem.pages.MenuPage;
import io.appium.java_client.AppiumDriver;

public class User {

    AppiumDriver driver;

    public User(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() { return new LoginPage(driver);}
    public MenuPage menuPage() { return new MenuPage(driver);}
    public MakePaymentPage makePaymentPage(){return new MakePaymentPage(driver);}
}
