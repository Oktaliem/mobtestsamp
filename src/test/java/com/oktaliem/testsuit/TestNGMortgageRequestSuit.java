package com.oktaliem.testsuit;

import io.qameta.allure.Feature;
import org.testng.annotations.Test;

/**
 * @Author Okta Liem
 */
@Feature("Make Payment")
public class TestNGMortgageRequestSuit extends Preparation {

    @Test
    public void mortgageRequest() {
        user_is_on.menuPage().goToMortgageRequestForm();
        user_is_on.mortgageRequestPage().inputRequestForm();
        user_is_on.mortgageRequestPage().checkIfRequestIfSuccessful();
    }

}
