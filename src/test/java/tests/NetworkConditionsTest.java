package tests;

import java.util.Optional;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v107.network.Network;
import org.openqa.selenium.devtools.v107.network.model.ConnectionType;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

public class NetworkConditionsTest {

    private EdgeDriver driver;
    private DevTools devTools;

    @DataProvider
    public Object[][] networkBandwidths() {
        return new Object[][]{
                {100000, 1000}, // download, upload (B/s)
                {10000, 1000},
                {1000, 1000},
                {0, 0} // default
        };
    }

    // I
    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
        devTools = driver.getDevTools();
        driver.get("https://RexJones2.com");
        devTools.createSession();
    }

    @Test(dataProvider = "networkBandwidths")
    public void testEnableSlowPage(int downloadSpeed, int uploadSpeed) {
        devTools.send(Network.emulateNetworkConditions(
                false,
                10, // ms
                downloadSpeed,
                uploadSpeed,
                Optional.of(ConnectionType.WIFI)
        ));

        long startTime = System.currentTimeMillis();
        driver.get("https://RexJones2.com");
        long endTime = System.currentTimeMillis();
        System.out.println("Download speed: " +
                (downloadSpeed == 0 ? "default" : downloadSpeed / 1000 + " KB/s") +
                " | Title: " + driver.getTitle() +
                " | Page load time: " + (endTime - startTime) + "ms"
        );
    }

    @AfterClass
    public void tearDown() {
        devTools.close();
        driver.quit();
    }

//    // II
//    @BeforeMethod
//    public void setUp() {
//        WebDriverManager.edgedriver().setup();
//        driver = new EdgeDriver();
//        driver.manage().window().maximize();
//        devTools = driver.getDevTools();
//        devTools.createSession();
//    }
//
//    @Test(dataProvider = "networkBandwidths")
//    public void testEnableSlowPage(int downloadSpeed, int uploadSpeed) {
//        devTools.send(Network.emulateNetworkConditions(
//                false,
//                10, // ms
//                downloadSpeed,
//                uploadSpeed,
//                Optional.of(ConnectionType.WIFI)
//        ));
//
//        long startTime = System.currentTimeMillis();
//        driver.get("https://RexJones2.com");
//        long endTime = System.currentTimeMillis();
//        System.out.println("Download speed: " +
//                (downloadSpeed == 0 ? "default" : downloadSpeed / 1000 + " KB/s") +
//                " | Title: " + driver.getTitle() +
//                " | Page load time: " + (endTime - startTime) + "ms"
//        );
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        devTools.close();
//        driver.quit();
//    }
}
