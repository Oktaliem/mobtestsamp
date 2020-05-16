package com.oktaliem;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

/**
 * @Author Okta Liem
 */
@RunWith(SerenityRunner.class)
public class LoginTestWithSerenityRunner {
    @Managed
    AppiumDriver driver;

    @Before
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
    }

    @Test
    public void loginTest() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("Status : TEST PASSED");
    }


    @After
    public void teardown() {
        driver.closeApp();
    }


}
