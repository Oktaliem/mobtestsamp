package com.oktaliem.testsuite.TestNG;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

/**
 * @Author Okta Liem
 */
@Feature("Mortgage Request")
public class TestNGMortgageRequestTest extends Preparation {

    @Test
    public void mortgageRequest() {
        user_is_on.menuPage().goToMortgageRequestForm();
        user_is_on.mortgageRequestPage()
                .inputRequestForm()
                .checkIfRequestIfSuccessful();
    }

}
