package com.oktaliem.utils;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.exception.DeviceNotFoundException;
import com.testinium.deviceinformation.model.Device;

import java.io.IOException;

/**
 * @Author Okta Liem
 */
public class GetDeviceInfo {
    public static void main(String args[]) throws IOException, DeviceNotFoundException {
        DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ALL);
        if (deviceInfo.anyDeviceConnected()) {
            Device device = deviceInfo.getFirstDevice();
            System.out.println("1 Device Build: " + device.getBuildVersion());
            System.out.println("2 Device Name: " + device.getDeviceProductName());
            System.out.println("3 Device Model Name: " + device.getModelNumber());
            System.out.println("4 Device Version: " + device.getProductVersion());
            System.out.println("5 Device Serial Number: " + device.getSerialNumber());
            System.out.println("6 Device id: " + device.getUniqueDeviceID());
            System.out.println("7 Device Integrated Circuit Card Identity: " + device.getIntegratedCircuitCardIdentity());
        }
    }

}
