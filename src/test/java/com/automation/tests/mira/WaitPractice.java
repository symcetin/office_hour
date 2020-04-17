package com.automation.tests.mira;


import com.automation.utilities.Driver;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WaitPractice extends com.cybertek.base.TestBase {

    private WebDriverWait wait;

    LoginPage loginPage = new LoginPage();
    private WebDriver driver = Driver.getDriver();

    @Test
    public void test_wait(){
        wait = new WebDriverWait(driver, 15);
        loginPage.login();
        wait.until(ExpectedConditions.titleIs("Dashboard"));

    }
}
