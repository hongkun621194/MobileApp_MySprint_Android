package SPRCOM_92052_Android_Sales;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92052 My Sprint App Android - Payment")
@Feature("SPRCOM-105700 My Sprint App Android - LoginAO Sales")
public class SPRCOM_105700_Android_LoginAO_Login extends MainBase {

    @Test(groups = {"LoginAO_Sales"}, priority = 2)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105702 My Sprint App Android - MDN Change")
    public void SPRCOM_105702() throws Exception
    {
        SPRCOM_105702_Step1();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Sign out\")");
        try {
            if(error_m.size() > 0) {
                System.out.println("User already Signed in");
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/negative_btn").click();
            } else {
                SPRCOM_105702_Step2();
                SPRCOM_105702_Step3();
                SPRCOM_105702_Step4();
                SPRCOM_105702_Step5();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("1. Tap the profile icon in the upper right corner")
    private void SPRCOM_105702_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Drop down sheet is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementsByClassName("android.widget.ImageView").get(0).click();


            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }
    @Step("2. Tap Settings in the drop down sheet")
    private void SPRCOM_105702_Step2()
    {
        saveTextLog_Allure_er("Sign in page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"Settings\")").click();
    }
    @Step("3. Scroll down and change MDN")
    private void SPRCOM_105702_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("New MDN is entered");

//        TouchAction t = new TouchAction(Android_Driver);

        //tap by few fingers
//        MultiTouchAction multiTouch = new MultiTouchAction(Android_Driver);
        Point point1 = Android_Driver.findElementById("com.sprint.care.beta:id/about_title").getLocation();
//        for (int i = 0; i < 10; i++) {
//            TouchAction tap = new TouchAction(Android_Driver);
//            multiTouch.add(tap.press(PointOption.point(point1))
//                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(100)))
//                    .release().perform());
//        }

        TouchAction t = new TouchAction(Android_Driver);
        Thread.sleep(500);
        for (int i = 0; i < 10; i++) {
            t.tap(PointOption.point(point1)).release().perform();
        }

        Thread.sleep(500);
        //LongPress, duration and release -- Scroll down - with elements ID selected
        WebElement scroll_point1 = Android_Driver.findElementById("com.sprint.care.beta:id/about_title");
        WebElement scroll_point2 = Android_Driver.findElementsById("com.sprint.care.beta:id/title_tv").get(0);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();

        Thread.sleep(500);
        //LongPress, duration and release -- Scroll down - with elements ID selected
        WebElement scroll_point3 = Android_Driver.findElementById("com.sprint.care.beta:id/clear_data_btn");
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point3))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point1))
                .release()
                .perform();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/mdn_text").clear();
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/mdn_text").sendKeys("5039759318");
    }
    @Step("4. Click 'set' button on the bottom of the page")
    private void SPRCOM_105702_Step4()
    {
        saveTextLog_Allure_er("First launch page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/set_btn").click();
    }
    @Step("5. Tap 4 times of 'Continue' on these pages")
    private void SPRCOM_105702_Step5() throws InterruptedException
    {
        saveTextLog_Allure_er("Main page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> button_cont = Android_Driver.findElementsById("com.sprint.care.beta:id/continueBtn");
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
                Android_Driver.findElementByAndroidUIAutomator("text(\"CONTINUE\")").click();

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
    }

    @Test(groups = {"LoginAO_Sales"}, priority = 3)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105704 Test Login with type of account owner")
    public void SPRCOM_105704() throws Exception
    {
        SPRCOM_105704_Step1();
        SPRCOM_105704_Step2();
        SPRCOM_105704_Step3();
        SPRCOM_105704_Step4();
        SPRCOM_105704_Step5();
    }

    @Step("1. Verify the main page is displayed and tap 'more info' icon")
    private void SPRCOM_105704_Step1()
    {
        saveTextLog_Allure_er("Drop down sheet is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementsByClassName("android.widget.ImageView").get(0).click();
            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Tap 'Sign in'")
    private void SPRCOM_105704_Step2()
    {
        saveTextLog_Allure_er("Sign in page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"Sign in\")").click();
    }


    @Step("3. Enter the username and password into the fields")
    private void SPRCOM_105704_Step3()
    {
        saveTextLog_Allure_er("Username and password are entered");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/userNameTextField").sendKeys("TWREG-51492");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/passwordTextField").sendKeys("T3stM3.P1s");
    }

    @Step("4. Tap 'Sign in' button")
    private void SPRCOM_105704_Step4()
    {
        saveTextLog_Allure_er("Sign in successfully");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/loginButton").click();
    }

    @Step("5. Verify the main page is displayed")
    private void SPRCOM_105704_Step5()
    {
        saveTextLog_Allure_er("Main page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() < 1) {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
        System.out.println("INFO: Login Successfully \n -----------------------------------------------");
    }
}
