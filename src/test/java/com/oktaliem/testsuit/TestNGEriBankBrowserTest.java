package com.oktaliem.testsuit;

import org.testng.annotations.Test;

public class TestNGEriBankBrowserTest extends Preparation{

    @Test
    public void browsingEriBackBrowser(){
        user_is_on.menuPage().goToAdvanceActionsForm();
        user_is_on.eriBankBrowserPage().openWebBrowser();
        user_is_on.eriBankBrowserPage().navigateToExperitestSite();
    }
}
