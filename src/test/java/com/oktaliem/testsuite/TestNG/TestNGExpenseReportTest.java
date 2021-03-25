package com.oktaliem.testsuite.TestNG;

import org.testng.annotations.Test;

/**
 * @Author Okta Liem
 */
public class TestNGExpenseReportTest extends Preparation{

    @Test
    public void createExpenseReport(){
        user_is_on.menuPage().goToExpenseReportForm();
        user_is_on.expenseReportPage().createExpenseReport().expenseReportCreatedSuccessfully();
    }
}
