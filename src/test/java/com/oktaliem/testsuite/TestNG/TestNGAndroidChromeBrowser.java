package com.oktaliem.testsuite.TestNG;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNGAndroidChromeBrowser {

    private AppiumDriver driver;
    private static DeviceInfo deviceInfo;

    @BeforeMethod
    public void prepareCapabilities() throws IOException, DeviceNotFoundException {
        /**
         * These capabilities can be used both Emulator and Read Device
         * Download chrome driver here https://chromedriver.chromium.org/downloads
         * Run appium for simulator $ appium --chromedriver-executable e.g /Users/oktaliem/chromedriver83/chromedriver
         * Run appium for samsung note 8 $ appium --chromedriver-executable e.g /Users/oktaliem/chromedriver
         * For Mac, if find "developer cannot be verified...", $ xattr -d com.apple.quarantine chromedriver (open terminal where chromedriver is placed)
         **/
        deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
        Device device = deviceInfo.getFirstDevice();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", device.getProductVersion());
        capabilities.setCapability("platformName", device.getDeviceProductName());
        capabilities.setCapability("deviceName", device.getUniqueDeviceID());
        capabilities.setCapability(CapabilityType.BROWSER_NAME,"Chrome");
        capabilities.setCapability("automationName", "UIAutomator2");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testChromeBrowser() throws InterruptedException {
        driver.get("https://stackoverflow.com/");
        driver.findElement(By.xpath("//div[@class='ps-relative p-speech-bubble--right h100 p24 bblr-lg btlr-lg " +
                "btrr-lg bg-orange-100 fc-black-800']/a[.='Join the community']")).click();
    }


    @AfterMethod
    public void teardown() throws Exception {
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath = "target/chromeBrowser.mp4";
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
