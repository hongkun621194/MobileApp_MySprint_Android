package Android_Base;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Example.saveScreenshotPNG_Allure;
import static Listeners_Tests.Listeners_Example.saveTextLog_Allure;

public class MainBase {

    public static IOSDriver<IOSElement> iosDriver=null;

    @BeforeSuite(groups = "MainBase")
    @Severity(SeverityLevel.CRITICAL)
    public static IOSDriver<IOSElement> setUp() throws Exception {
        File app = new File("src/main/java/app","SprintBeta.apk");
        
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(MobileCapabilityType.DEVICE_NAME, "appium26");
        d.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        d.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        iosDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), d);

        System.out.println("System: \n My Sprint Beta - \n Test Device: Android \n App Version: Latest(Unknown) " +
                "\n Others: N/A \n -----------------------------------------------");

        return iosDriver;
    }

    @AfterSuite(groups = "MainBase")
    public void tearDown() {
        iosDriver.quit();
        System.out.println("System: Test Done, Android Driver Quitting ...\n -----------------------------------------------");
    }

    @BeforeTest(groups = {"MainBase"})
    @Severity(SeverityLevel.CRITICAL)
    public void pagesFirstLaunch() throws Exception {

        // Allow notification
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("Allow").click();

        iosDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        List<IOSElement> button_cont = iosDriver.findElementsById("Continue");
        try {
            if (button_cont.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Continue").click();

                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("CONTINUE").click();

                System.out.println("INFO: Pre-pages passing ... DONE \n --------------------------------------------");
                Thread.sleep(3000);
            } else {
                System.out.println("INFO: Server error ... ... Driver Quitting ");
                Assert.fail();
                saveScreenshotPNG_Allure(iosDriver);
                saveTextLog_Allure("INFO: Server error ... ... Driver Quitting");
                iosDriver.quit();
            }
        } catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
    }
}
