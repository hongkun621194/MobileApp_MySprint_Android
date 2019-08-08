package Android_Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.saveScreenshotPNG_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;

public class MainBase {

    public static AndroidDriver<AndroidElement> Android_Driver=null;
    public static String notifAccess = "ALLOW";

    @BeforeSuite(groups = "MainBase")
    @Parameters({"udid", "appiumPort", "deviceName"})
    @Severity(SeverityLevel.CRITICAL)
    public static AndroidDriver<AndroidElement> setUp(String udid, String appiumPort,
                                                      String deviceName) throws Exception {

        if(deviceName.equals("appium29")) {
            notifAccess = "Allow all the time";
        }

        File app = new File("src/main/java/app","care-beta-616012.apk");
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        d.setCapability(MobileCapabilityType.UDID, udid);
        d.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        d.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        Android_Driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + appiumPort + "/wd/hub"), d);

        System.out.println("System: \n My Sprint Beta - \n Test Device: Android \n App Version: Latest(Unknown) " +
                "\n Others: Thread 1 \n -----------------------------------------------");

        return Android_Driver;
    }

    @AfterSuite(groups = "MainBase")
    public void tearDown() {
        Android_Driver.quit();
        System.out.println("System: Test Done, Android Driver Quitting ...\n -----------------------------------------------");
    }

    @BeforeTest(groups = {"MainBase"})
    @Severity(SeverityLevel.CRITICAL)
    public void pagesFirstLaunch() throws Exception {
        Thread.sleep(5000);
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> button_cont = Android_Driver.findElementsById("com.sprint.care.beta:id/continueBtn");
//        List<AndroidElement> button_cont = Android_Driver.findElementsByXPath("//android.widget.Button[@text='Continue']");

        try {
            if (button_cont.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                Android_Driver.findElementByXPath("//android.widget.Button[@text='Continue']").click();
                Android_Driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();

                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                Android_Driver.findElementByXPath("//android.widget.Button[@text='Continue']").click();
                Android_Driver.findElementByAndroidUIAutomator("text(\"Continue\")").click();

                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                Android_Driver.findElementByXPath("//android.widget.Button[@text='CONTINUE']").click();
                Android_Driver.findElementByAndroidUIAutomator("text(\"CONTINUE\")").click();

                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//                Android_Driver.findElementByXPath("//android.widget.Button[@text='CONTINUE']").click();
                Android_Driver.findElementById("com.sprint.care.beta:id/continue_btn").click();

                System.out.println("INFO: Pre-pages passing ... DONE \n --------------------------------------------");
            } else {
                System.out.println("INFO: Server error ... ... Driver Quitting ");
                Assert.fail();
                saveScreenshotPNG_Allure(Android_Driver);
                saveTextLog_Allure("INFO: Server error ... ... Driver Quitting");
                Android_Driver.quit();
            }
        } catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }

        // Allow notification
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        Android_Driver.findElementByXPath("//android.widget.Button[@text='ALLOW']").click();
        Android_Driver.findElementByAndroidUIAutomator("text(\""+ notifAccess +"\")").click();

        Thread.sleep(3000);
    }
}
