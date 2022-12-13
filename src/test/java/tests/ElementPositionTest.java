package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ElementPositionTest extends BaseTest {

    // Inspect element: Properties > height, width, x, y
    @Test
    public void testGetPositionDimension() {
        delay();
        WebElement ohrmBranding = driver.findElement(
                By.cssSelector(".orangehrm-login-branding > img"));
        Rectangle ohrmRect = ohrmBranding.getRect();
        System.out.println("height: " + ohrmRect.getHeight());
        System.out.println("width: " + ohrmRect.getWidth());
        System.out.println("x: " + ohrmRect.getX());
        System.out.println("y: " + ohrmRect.getY());
    } // x and y show different values than in Properties ???
}
