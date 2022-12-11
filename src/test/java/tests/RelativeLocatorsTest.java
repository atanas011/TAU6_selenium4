package tests;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsTest extends BaseTest {

    @Test
    public void testCredentials() {
        delay();
        WebElement loginTitle = driver.findElement(By.tagName("h5"));
        WebElement form = driver.findElement(By.tagName("form"));

        List<WebElement> credentials = driver.findElements(RelativeLocator.with(
                By.tagName("p")).above(form).below(loginTitle) // the order is important
        );
        credentials.forEach(e -> System.out.println(e.getText()));
    }

    @Test
    public void testSocialMediaLinks() {
//        delay();
        List<WebElement> socialMediaLinks = driver.findElements(with(
                By.tagName("a")).near(By.className("orangehrm-login-footer-sm"))
        );
        socialMediaLinks.forEach(e -> System.out.println(e.getAttribute("href")));
    }
}
