package tests;

import java.io.File;
import java.io.IOException;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class ScreenshotsTest extends BaseTest {

    @Test
    public void testTakeWebElementScreenshot() throws IOException {
        delay();
        WebElement ohrmBranding = driver.findElement(
                By.cssSelector(".orangehrm-login-branding > img"));
        File source = ohrmBranding.getScreenshotAs(OutputType.FILE);
        File destination = new File("webelement screenshot test.png");
        FileHandler.copy(source, destination); // I
//        FileUtils.copyFile(source, destination); // II
    }

//    @Test
//    public void testTakeFullPageScreenshot() throws IOException {
//        // only for FirefoxDriver
//        File source = ((FirefoxDriver) driver).getFullPageScreenshotAs(OutputType.FILE);
//        FileHandler.copy(source, new File("full page screenshot test.png"));
//    }
}
