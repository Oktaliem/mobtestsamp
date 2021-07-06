package com.oktaliem.testsuite.TestNG;

import com.oktaliem.User;
import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Preparation {
    AppiumDriver driver;
    User user_is_on;
    public static DeviceInfo deviceInfo;
    String testcase = "Video";

    @Step("Prepare Capabilities")
    @BeforeMethod
    public void prepareCapabilities(Method method) throws IOException, DeviceNotFoundException {
        String platform = "Android"; // iOSNative, iOSSimulator

        if (platform.equals("Android")){
            deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
            Device device = deviceInfo.getFirstDevice();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformVersion", device.getProductVersion());
            capabilities.setCapability("platformName", device.getDeviceProductName());
            capabilities.setCapability("deviceName", device.getUniqueDeviceID());
            capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/eribank.apk");
            capabilities.setCapability("appPackage", "com.experitest.eribank");
            capabilities.setCapability("appActivity", "com.experitest.ExperiBank.LoginActivity");
            capabilities.setCapability("automationName", "UiAutomator2");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    if(platform.equals("iOSSimulator")){
            deviceInfo = new DeviceInfoImpl(DeviceType.IOSSIMULATOR);
            Device device = deviceInfo.getFirstDevice();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformVersion", device.getProductVersion());
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/ExperiBank.app");
            capabilities.setCapability("automationName", "XCUITest");
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }

    if (platform.equals("iOSNative")) {
        //masihgagal
        deviceInfo = new DeviceInfoImpl(DeviceType.IOS);
        Device device = deviceInfo.getFirstDevice();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", device.getProductVersion());
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "yout_iphone_name");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/EriBank.ipa");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("udid", "xxxxxxxxx");
        capabilities.setCapability("xcodeSigningId", "iPhone Developer");
        capabilities.setCapability("xcodeOrgId", "xxxxxx");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBank");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
        this.testcase = method.getName() + "_" + platform;
        ((CanRecordScreen) driver).startRecordingScreen();
        user_is_on = new User(driver);
        user_is_on.loginPage().loginToApp();
        user_is_on.loginPage().landingToMenuPage();
    }


    @AfterMethod
    public void teardown() throws Exception {
        /**
         * Video Recording Capabilities:
         * Android Device + Native app -> Passed
         * Android Device + Chrome Browser -> Unable to record
         * Android Emulator + Native app -> Passed
         * Android Emulator + Chrome Browser -> Unable to record
         * iOS Simulator + Native app -> Unable to record
         * iOS Simulator + Safari Browser -> Unable to record
         * iOS Device + Native app -> TO DO
         * IOS DEVICE + Safari Browser -> Unable to record
         */
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath = "target/" + testcase + ".mp4";
        Path path = Paths.get(destinationPath);
        Files.write(path, data);
        attachVideo(destinationPath);
        driver.quit();
    }

    @Attachment(value = "video", type = "video/mp4")
    public byte[] attachVideo(String path) throws Exception {
        return getFile(path);
    }

    public byte[] getFile(String fileName) throws Exception {
        File file = new File(fileName);
        return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }

}
