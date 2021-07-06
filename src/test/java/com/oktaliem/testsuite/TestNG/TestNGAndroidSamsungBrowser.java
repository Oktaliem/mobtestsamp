package com.oktaliem.testsuite.TestNG;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestNGAndroidSamsungBrowser {

    private WebDriver driver;
    private static DeviceInfo deviceInfo;

    @BeforeMethod
    public void prepareCapabilities() throws IOException, DeviceNotFoundException {
        /**
         * Issues
         * https://stackoverflow.com/questions/52257711/test-automation-using-samsung-internet-browser-using-appium
         * https://github.com/SamsungInternet/chromedriver-examples/issues
         */
        deviceInfo = new DeviceInfoImpl(DeviceType.ANDROID);
        Device device = deviceInfo.getFirstDevice();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformVersion", device.getProductVersion());
        capabilities.setCapability("platformName", device.getDeviceProductName());
        capabilities.setCapability("deviceName", device.getUniqueDeviceID());
        capabilities.setCapability("appPackage","com.sec.android.app.sbrowser");
        capabilities.setCapability("appActivity","com.sec.android.app.sbrowser.SBrowserMainActivity");
        capabilities.setCapability("appDeviceSocket","Terrace_devtools_remote");
        capabilities.setCapability("appExecName","Terrace");
        capabilities.setCapability("noReset", "false");
        capabilities.setCapability("automationName", "UIAutomator2");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        ((CanRecordScreen) driver).startRecordingScreen();
    }

    @Test
    public void testSamsungInternetBrowser() throws InterruptedException {
        driver.get("https://duckduckgo.com");
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#search_form_input_homepage")).click();
    }


    @AfterMethod
    public void teardown() throws Exception {
        String base64String = ((CanRecordScreen) driver).stopRecordingScreen();
        byte[] data = Base64.decodeBase64(base64String);
        String destinationPath = "target/filename.mp4";
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
