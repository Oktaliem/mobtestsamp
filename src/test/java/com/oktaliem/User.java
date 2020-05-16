package com.oktaliem;

import com.oktaliem.pagesobject.LoginPage;
import io.appium.java_client.AppiumDriver;

public class User {

    AppiumDriver driver;

    public User(AppiumDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() { return new LoginPage(driver);}
}
