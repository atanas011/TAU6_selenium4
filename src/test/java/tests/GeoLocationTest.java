package tests;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoLocationTest {

    private EdgeDriver driver; // !!!

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testMockGeoLocation_exeCdpCommand() {
        Map<String, Object> coords = new HashMap<>() {{
            put("latitude", 44.773638); // latlong.net
            put("longitude", 20.498736);
            put("accuracy", 1);
        }};
        driver.executeCdpCommand( // chromedevtools.github.io/devtools-protocol > type: geo
                "Emulation.setGeolocationOverride", coords);
        driver.get("https://where-am-i.org/");
        assertEquals(driver.findElement(By.id("address")).getText(),
                "Radovana Simića-Cige 14, 11050 Belgrade, Serbia");
    }

    @Test
    public void testMockGeoLocation_devTools() {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Emulation.setGeolocationOverride(
                Optional.of(44.773638),
                Optional.of(20.498736),
                Optional.of(1)
        ));
        driver.get("https://where-am-i.org/");
        assertEquals(driver.findElement(By.id("address")).getText(),
                "Radovana Simića-Cige 14, 11050 Belgrade, Serbia");
        devTools.close();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
