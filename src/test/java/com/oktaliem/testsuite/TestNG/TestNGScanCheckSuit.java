package com.oktaliem.testsuite.TestNG;

import org.testng.annotations.Test;
/**
 * @Author Okta Liem
 */
public class TestNGScanCheckSuit extends  Preparation{

    @Test
    public void scanCheck(){
        user_is_on.menuPage().goToAdvanceActionsForm();
        user_is_on.scanCheckPage()
                .takeAPhoto()
                .ableToTakePhotoSuccessfully();
    }
}
