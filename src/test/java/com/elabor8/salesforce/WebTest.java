package com.elabor8.salesforce;




import com.gargoylesoftware.htmlunit.BrowserVersion;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.phantomjs.PhantomJSDriver;


import java.io.File;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;


public class WebTest {
    private static WebDriver driver;

    @Test
    public void testEasy() throws Exception {

        driver.manage().timeouts().implicitlyWait(100, SECONDS );
//        driver.get("https://alan-elabor8-dev-ed.lightning.force.com/one/one.app#/n/ContactListTab");
//        driver.findElement(By.id("username")).sendKeys("alan@elabor8.com.au");

        driver.get("https://alan2-elabor8-dev-ed.lightning.force.com/one/one.app#/n/ContactListTab");
        driver.findElement(By.id("username")).sendKeys("alan.elabor8.dev@gmail.com");

        driver.findElement(By.id("password")).sendKeys("e8techuser");
        driver.findElement(By.id("Login")).click();

        Select source = new Select(driver.findElement(By.name("source")));
        List<WebElement> options = source.getOptions();

        boolean foundItem = false;
        for (WebElement we : options) {
            System.out.println("Options:" + we.getText());
            if (we.getText().equals("NEW MENU ITEM")) foundItem = true;
        }
        System.out.println(foundItem);
        Assert.assertTrue("NEW MENU ITEM not found", foundItem);
    }

    @BeforeClass
    public static void beforeTest() {
        boolean useChrome = Boolean.parseBoolean(System.getProperty("useChrome"));
        System.out.println("Using Chrome:" + useChrome);

        if (useChrome) {
            driver = new ChromeDriver();
        } else {
//            File file = new File("/Users/alanmangroo/phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs");
            File file = new File("/home/alan/phantomjs/phantomjs-2.1.1-linux-x86_64/bin/phantomjs");
            System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
            driver = new PhantomJSDriver();
        }
    }

    @AfterClass
    public static void afterTest() {
       driver.quit();
    }

}
