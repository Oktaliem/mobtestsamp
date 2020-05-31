package com.oktaliem.testsuite.TestNG;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

/**
 * @Author Okta Liem
 */
@Feature("Make Payment")
public class TestNGMakePaymentTest extends Preparation {

    @Test
    public void makePayment() {
        user_is_on.menuPage().goToMakePaymentForm();
        user_is_on.makePaymentPage()
                .inputPaymentInfo()
                .makePayment()
                .checkIfPaymentIfSuccessful();
    }

    @Test
    public void cancelMakePayment() {
        user_is_on.menuPage().goToMakePaymentForm();
        user_is_on.makePaymentPage()
                .inputPaymentInfo()
                .cancelMakePayment()
                .checkIfCancelPaymentIsSuccessful();
    }

    @Test
    public void cancelMakePaymentForm() {
        user_is_on.menuPage().goToMakePaymentForm();
        user_is_on.makePaymentPage()
                .inputPaymentInfo()
                .cancelMakePaymentForm()
                .checkIfCancelPaymentFormIsSuccessful();
    }
}
