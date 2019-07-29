package SPRCOM_92088_iOS_Sales;

import iOS_Tests.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Example.*;

@Listeners(Listeners_Tests.Listeners_Example.class)
@Epic("SPRCOM-92088 My Sprint App iOS - Sales")
@Feature("SPRCOM-107100 My Sprint App iOS - AccountOwner Login")
public class SPRCOM_107100_iOS_LoginAO_Login extends MainBase {

    @Test(groups = {"AccountOwner", "Login"}, priority = 1)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-107102 My Sprint App iOS - MDN Change")
    public void SPRCOM_107102() throws Exception
    {
        SPRCOM_107102_Step1();
        SPRCOM_107102_Step2();
        SPRCOM_107102_Step3();
        SPRCOM_107102_Step4();
        SPRCOM_107102_Step5();
        SPRCOM_107102_Step6();
    }

    @Step("1. Tap the profile icon in the upper right corner")
    private void SPRCOM_107102_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Bottom Sheet should pop up");
        Thread.sleep(1000);
        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(345, 45)).release().perform();
    }
    @Step("2. Tap App Settings in the bottom sheet")
    private void SPRCOM_107102_Step2()
    {
        saveTextLog_Allure_er("Settings page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("App Settings").click();
    }
    @Step("3. Scroll down and click ‘MDN’")
    private void SPRCOM_107102_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("MDN page is displayed");
        Thread.sleep(1000);
        //LongPress, duration and release -- Scroll down - with elements ID selected
        WebElement scroll_point1 = iosDriver.findElementByAccessibilityId("Acknowledgements");
        WebElement scroll_point2 = iosDriver.findElementByAccessibilityId("Open iPhone Settings");
        TouchAction t = new TouchAction(iosDriver);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("MDN").click();
    }
    @Step("4. Click Text field and enter the phone number")
    private void SPRCOM_107102_Step4()
    {
        saveTextLog_Allure_er("Number is entered");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByClassName("XCUIElementTypeTextField").sendKeys("5039759318");

    }
    @Step("5. Tap Save button and wait for finish loading")
    private void SPRCOM_107102_Step5() throws InterruptedException
    {
        saveTextLog_Allure_er("Current Page is loading and back to MDN page");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Save").click();
        Thread.sleep(3000);
    }
    @Step("6. Tap return icon and tap DONE")
    private void SPRCOM_107102_Step6()
    {
        saveTextLog_Allure_er("Main page is loading and Continue button is displayed if load successfully");
        iosDriver.navigate().back();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Done").click();
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Continue");
        try {
            if(payment_m.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Continue").click();
                System.out.println("INFO: MDN Change Successfully");
                saveTextLog_Allure("INFO: MDN Change Successfully");
            } else {
                System.out.println("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }


    @Test(groups = {"AccountOwner", "Login"}, priority = 2)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-107104 My Sprint App iOS - Account Owner Login")
    public void SPRCOM_107104() throws Exception
    {
        SPRCOM_107104_Step1();
    }

    @Step("1. Make sure button ‘Make a payment’ showed in main page")
    private void SPRCOM_107104_Step1() throws Exception
    {
        saveTextLog_Allure_er("Button ‘Make a payment’ is displayed in main page");
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Make a payment");
        try {
            if(payment_m.size() > 0) {
                SPRCOM_107104_Step2();
                SPRCOM_107104_Step3();
                SPRCOM_107104_Step4();
            } else {
                System.out.println("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Tap the profile icon in the upper right corner and click ‘Sign in’ in the bottom sheet")
    private void SPRCOM_107104_Step2() throws InterruptedException
    {
        saveTextLog_Allure_er("‘Sign in’ page is displayed");
        TouchAction t = new TouchAction(iosDriver);
        Thread.sleep(500);
        t.tap(PointOption.point(345, 45)).release().perform();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Sign in").click();
    }

    @Step("3. Enter username and password")
    private void SPRCOM_107104_Step3()
    {
        saveTextLog_Allure_er("Username and password are entered");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Username").sendKeys("TWREG-51492");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Password").sendKeys("T3stM3.P1s");
    }

    @Step("4. Tap Sign in button")
    private void SPRCOM_107104_Step4() throws Exception
    {
        saveTextLog_Allure_er("Sign in Success, main page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Sign In").click();
    }
}
