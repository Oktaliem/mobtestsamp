package com.oktaliem.testsuite.TestNG;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
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

public class TestNGAndroidSafariBrowser {

    private AppiumDriver driver;
    private static DeviceInfo deviceInfo;
    private String iphone = "device";

    @BeforeMethod
    public void prepareCapabilities() throws IOException, DeviceNotFoundException {
        if(this.iphone.equals("simulator")) {
            deviceInfo = new DeviceInfoImpl(DeviceType.IOSSIMULATOR);
            Device device = deviceInfo.getFirstDevice();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformVersion", device.getProductVersion());
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("browserName", "Safari");
            capabilities.setCapability("automationName", "XCUITest");
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            ((CanRecordScreen) driver).startRecordingScreen();
        }

        if(this.iphone.equals("device")) {
            deviceInfo = new DeviceInfoImpl(DeviceType.IOS);
            Device device = deviceInfo.getFirstDevice();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformVersion", device.getProductVersion());
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "your_iPhone_name");
            capabilities.setCapability("browserName", "Safari");
            capabilities.setCapability("udid", "yout_iPhone_udid");
            capabilities.setCapability("startIWDP", true);
            driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            ((CanRecordScreen) driver).startRecordingScreen();
        }
    }

    @Test
    public void testSafariBrowser() throws InterruptedException {
        driver.get("https://stackoverflow.com/");
        driver.findElement(By.xpath("//div[@class='ps-relative p-speech-bubble--right h100 p24 bblr-lg btlr-lg " +
                "btrr-lg bg-orange-100 fc-black-800']/a[.='Join the community']")).click();
        Thread.sleep(10000);
    }


    @AfterMethod
    public void teardown() throws Exception {
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath = "target/SafariBrowser.mp4";
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
