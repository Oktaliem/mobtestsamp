package com.oktaliem;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URL;

/**
 * @Author Okta Liem
 */
public class LoginTestWithTestng {

    AppiumDriver driver;
    User user;

    @BeforeMethod
    public void prepareCapabilities() throws IOException, DeviceNotFoundException {
        DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
        Device device = deviceInfo.getFirstDevice();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", device.getDeviceProductName());
        capabilities.setCapability("deviceName", device.getUniqueDeviceID());
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/eribank.apk");
        capabilities.setCapability("appPackage", "com.experitest.eribank");
        capabilities.setCapability("appActivity", "com.experitest.ExperiBank.LoginActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        user = new User(driver);
    }

    @Test
    public void loginTest() {
        user.loginPage().loginToApp();
        System.out.println("TestNG Status : TEST PASSED");
    }

    @AfterMethod
    public void teardown() {
        driver.closeApp();
    }
}
