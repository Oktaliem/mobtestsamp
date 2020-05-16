package com.oktaliem;

import com.oktaliem.pagesobject.LoginPage;
import com.oktaliem.pagesobject.MenuPage;
import io.appium.java_client.AppiumDriver;

public class User {

    AppiumDriver driver;

    public User(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() { return new LoginPage(driver);}
    public MenuPage menuPage() { return new MenuPage(driver);}
}
