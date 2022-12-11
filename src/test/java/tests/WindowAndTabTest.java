package tests;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

public class WindowAndTabTest extends BaseTest {

    @Test
    public void testNewWindowTab() {
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
        newWindow.get("https://www.orangehrm.com/");
        System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testWorkingInBothTabs() {
        driver.switchTo().newWindow(WindowType.TAB)
                .get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode");

        delay();
        driver.findElement(By.className("oxd-input--active")).sendKeys("Admin");
        driver.findElement(By.className("orangehrm-forgot-password-button--reset")).click();
        delay();
        WebElement title = driver.findElement(By.className("orangehrm-forgot-password-title"));
        System.out.println("Forgot password title: " + title.getText());

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterate = handles.iterator();
        String firstTab = iterate.next();

        driver.switchTo().window(firstTab);
        System.out.println("Login title: " + driver.findElement(By.tagName("h5")).getText());
    }
}
