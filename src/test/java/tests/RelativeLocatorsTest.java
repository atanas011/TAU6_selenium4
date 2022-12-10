package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.*;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        delay();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCredentials() {
        WebElement loginTitle = driver.findElement(By.tagName("h5"));
        WebElement form = driver.findElement(By.tagName("form"));

        List<WebElement> credentials = driver.findElements(RelativeLocator.with(
                By.tagName("p")).above(form).below(loginTitle) // the order is important
        );
        credentials.forEach(e -> System.out.println(e.getText()));
    }

    @Test
    public void testSocialMediaLinks() {
        List<WebElement> socialMediaLinks = driver.findElements(with(
                By.tagName("a")).near(By.className("orangehrm-login-footer-sm"))
        );
        socialMediaLinks.forEach(e -> System.out.println(e.getAttribute("href")));
    }

    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
