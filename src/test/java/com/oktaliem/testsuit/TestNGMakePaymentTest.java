package com.oktaliem.testsuit;

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
        user_is_on.makePaymentPage().inputPaymentInfo();
        user_is_on.makePaymentPage().makePayment();
        user_is_on.makePaymentPage().checkIfPaymentIfSuccessful();
    }

    @Test
    public void cancelMakePayment() {
        user_is_on.menuPage().goToMakePaymentForm();
        user_is_on.makePaymentPage().inputPaymentInfo();
        user_is_on.makePaymentPage().cancelMakePayment();
        user_is_on.makePaymentPage().checkIfCancelPaymentIsSuccessful();
    }

    @Test
    public void cancelMakePaymentForm() {
        user_is_on.menuPage().goToMakePaymentForm();
        user_is_on.makePaymentPage().inputPaymentInfo();
        user_is_on.makePaymentPage().cancelMakePaymentForm();
        user_is_on.makePaymentPage().checkIfCancelPaymentFormIsSuccessful();
    }
}
