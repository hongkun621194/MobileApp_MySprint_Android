package iOS_Base;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_iOS.saveScreenshotPNG_Allure;
import static Listeners_Tests.Listeners_iOS.saveTextLog_Allure;

public class MainBase {

    public static IOSDriver<IOSElement> iosDriver=null;
    
    public static void unzip(File appZip, String dest, String pw){
        try {
            ZipFile zipFile = new ZipFile(appZip);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(pw);
            }
            zipFile.extractAll(dest);
        } catch (ZipException e) {
            e.printStackTrace();
        }
    }
    
    @BeforeTest(groups = "MainBase")
    @Severity(SeverityLevel.CRITICAL)
    public static IOSDriver<IOSElement> setUp() throws Exception {
        String destDir = "src/main/java/app/";
        String appUrl = "https://sprintappfiles.s3.amazonaws.com/SprintBeta.app.zip";

        boolean exist_file = new File(destDir + "SprintBeta.app.zip").exists();
        if(!exist_file) {
            FileUtils.copyURLToFile(new URL(appUrl), new File(destDir + "SprintBeta.app.zip"));
            File appZip = new File(destDir+"SprintBeta.app.zip");
            unzip(appZip, destDir, "None");
        }
        File app = new File("src/main/java/app","SprintBeta.app");
        
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
        d.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        d.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
        d.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        d.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        iosDriver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), d);
        System.out.println("System: \n My Sprint Beta - \n Test Device: IPhone 8 \n App Version: Latest(Unknown) " +
                "\n Others: N/A \n -----------------------------------------------");

        return iosDriver;
    }

    @AfterTest(groups = "MainBase")
    public void tearDown() {
        iosDriver.quit();
        System.out.println("System: Test Done, iOS Driver Quitting ...\n -----------------------------------------------");
    }

    @Test(groups = {"MainBase"}, priority = 0)
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
