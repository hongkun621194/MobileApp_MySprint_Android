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
@Feature("SPRCOM-107200 My Sprint App iOS - LoginAO Sales")
public class SPRCOM_107200_iOS_LoginAO_Sales_Checkout_Cart extends MainBase {

    @Test(groups = {"AccountOwner", "Login"}, priority = 3)
    @Description("My Sprint App iOS - Check out Test")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-107204 My Sprint App iOS - Check out Test")
    public void SPRCOM_107204() throws InterruptedException
    {
        SPRCOM_107204_Step1();
        SPRCOM_107204_Step2();
        SPRCOM_107204_Step3();
        SPRCOM_107204_Step4();
        SPRCOM_107204_Step5();
        SPRCOM_107204_Step6();
        SPRCOM_107204_Step7();
        SPRCOM_107204_Step8();
    }

    @Step("1. Tap cart icon in the upper right")
    private void SPRCOM_107204_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Cart page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Make a payment");
        try {
            if(payment_m.size() < 1) {
                System.out.println("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(315, 45)).release().perform();
    }

    @Step("2. Tap button ‘Check out’")
    private void SPRCOM_107204_Step2() throws InterruptedException
    {
        saveTextLog_Allure_er("Number page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Check out").click();
    }

    @Step("3. Tap ‘get a new number’ and Continue")
    private void SPRCOM_107204_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("Wait for loading(15s), Personal Info page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Get a new number").click();

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Continue").click();

    }

    @Step("4. Enter First name and Last name")
    private void SPRCOM_107204_Step4() throws InterruptedException
    {
        saveTextLog_Allure_er("First name and Last name are entered");
        iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<IOSElement> Device_Detail = iosDriver.findElementsByClassName("XCUIElementTypeTextField");

        for (IOSElement iosElement : Device_Detail) {
            System.out.println(iosElement.getLocation());
        }
        Device_Detail.get(0).clear();
        Thread.sleep(1000);
        Device_Detail.get(0).sendKeys("Max");
        Thread.sleep(1000);
        Device_Detail.get(1).clear();
        Thread.sleep(1000);
        Device_Detail.get(1).sendKeys("Jin");

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("Done").click();

    }

    @Step("5. Tap Button Continue")
    private void SPRCOM_107204_Step5() throws InterruptedException
    {
        saveTextLog_Allure_er("Delivery Options page(5s) is displayed");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("PASS: Continue - status is enabled");

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Continue").click();

    }

    @Step("6. Tap Overnight shipping, and tap Continue")
    private void SPRCOM_107204_Step6() throws InterruptedException
    {
        saveTextLog_Allure_er("Summary page is displayed");

//        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        iosDriver.findElementByAccessibilityId("Overnight shipping").click();

        Thread.sleep(3000);
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("PASS: Continue - status is enabled");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Continue").click();

        Thread.sleep(3000);
        System.out.println("1check!");
    }

    @Step("7. Enter CVV and tap Submit order")
    private void SPRCOM_107204_Step7() throws InterruptedException
    {
        saveTextLog_Allure_er("Rate dialog pop up (decline- “OK”)?");

        System.out.println("2check!");
        iosDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        WebElement scroll_point1 = iosDriver.findElementByAccessibilityId("checkout");
        WebElement scroll_point2 = iosDriver.findElementByAccessibilityId("order summary");
        TouchAction t = new TouchAction(iosDriver);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();

        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<IOSElement> Device_Detail = iosDriver.findElementsByClassName("XCUIElementTypeSecureTextField");

        for (IOSElement iosElement : Device_Detail) {
            System.out.println(iosElement.getLocation());
        }

        Thread.sleep(2000);
        Device_Detail.get(0).sendKeys("000");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Done").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Submit order").isEnabled());
        System.out.println("PASS: Submit order - button status is enabled");

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Submit order").click();
    }

    @Step("8. Tap Not now")
    private void SPRCOM_107204_Step8() throws InterruptedException
    {
        saveTextLog_Allure_er("Payment result page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<IOSElement> Warning_m1 = iosDriver.findElementsById("Not Now");
        try {
            if(Warning_m1.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Not Now").click();

                iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                WebElement scroll_point1 = iosDriver.findElementByAccessibilityId("promotions and discounts");
                WebElement scroll_point2 = iosDriver.findElementByAccessibilityId("Order number");

                TouchAction t = new TouchAction(iosDriver);
                t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                        .withDuration(Duration.ofSeconds(1)))
                        .moveTo(ElementOption.element(scroll_point2))
                        .release()
                        .perform();
                Thread.sleep(500);
                iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                WebElement scroll_point3 = iosDriver.findElementByAccessibilityId("order summary");
                t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point3))
                        .withDuration(Duration.ofSeconds(1)))
                        .moveTo(ElementOption.element(scroll_point1))
                        .release()
                        .perform();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();
                Thread.sleep(5000);

            } else {
                // Please do not attempt to resubmit, as it may result in duplicate transaction
                System.out.println("ERROR: 'We are having technical difficulties. ...'");
                saveTextLog_Allure("ERROR: 'We are having technical difficulties. ...'");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel order and return to cart").click();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel").click();

                // ...........
                Thread.sleep(2000);
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
            Assert.fail();
        }
    }
}
