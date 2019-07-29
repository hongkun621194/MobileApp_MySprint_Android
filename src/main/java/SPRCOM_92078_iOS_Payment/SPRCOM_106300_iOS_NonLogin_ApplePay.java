package SPRCOM_92078_iOS_Payment;

import iOS_Tests.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Example.*;

@Listeners(Listeners_Tests.Listeners_Example.class)
@Epic("SPRCOM-92078 My Sprint App iOS - Payment")
@Feature("SPRCOM-106300 My Sprint App iOS - NonLogin Payment")
public class SPRCOM_106300_iOS_NonLogin_ApplePay extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 3)
    @Description("Payment making with ApplePay")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-106312 Payment making with ApplePay")
    public void SPRCOM_106306() throws InterruptedException
    {
        Button_ApplePay1();
        Button_Continue2();
        Payment_Amount3();
        Payment_Amount4();
        Payment_SpecialSign5();
        Payment_Cancel6();
    }

    @Step("1. Click Button and select 'Pay with Apple Pay'")
    private void Button_ApplePay1() throws InterruptedException
    {
        saveTextLog_Allure_er("ApplePay page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<IOSElement> pay_m =iosDriver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                iosDriver.findElementById("Make a payment").click();
                System.out.println("INFO: Click Button'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Thread.sleep(500); //Wait for Bottom Sheet pop up
        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(190, 575)).release().perform();
        System.out.println("INFO: Click Button and select 'Pay with Apple Pay'");
    }

    @Step("2. Check default status of button 'Continue'")
    private void Button_Continue2() throws InterruptedException
    {
        saveTextLog_Allure_er("The button default status should be disabled");
        Thread.sleep(500); //Loading next page
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue - Default status is disabled ... PASS");
    }

    @Step("3. input amount less than $1")
    private void Payment_Amount3() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Min. payment amount limit $1' should show up");
        // Step1
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("$0.00").sendKeys("99"); //Less than 1
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById(" - Min. payment amount limit $1");
        String error_message = "Min. payment amount limit $1";
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: Amount less than $1 - Error Message 'Min. payment amount limit $1' " +
                        "should show ... PASS");
                MakePayment_Amount_ContinueDisabled(error_message);

            } else {
                System.out.println("INFO: Amount less than $1 - Error Message" + error_message + "should show.");
                saveTextLog_Allure("Allure: Amount less than $1 - Error Message" + error_message + "should show.");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.fail();
                MakePayment_Amount_ContinueEnabled();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("4. input amount more than $2000")
    private void Payment_Amount4() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Max. payment amount limit $2000' should show up");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("$0.99").clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("200001"); //More than $2000
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById("- Max. payment amount limit $2000");
        String error_message = "- Max. payment amount limit $2000";
        try {
            if(error_m.size() > 0) {
                System.out.println("PASS: Amount more than $2000 - Error Message '- Max. payment amount limit $2000' " +
                        "should show");
                MakePayment_Amount_ContinueDisabled(error_message);

            } else {
                System.out.println("FAIL: Amount more than $2000 - Error Message '- Max. payment amount limit $2000' " +
                        "should show");
                saveTextLog_Allure("FAIL: Amount more than $2000 - Error Message '- Max. payment amount limit $2000' " +
                        "should show");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.fail();
                MakePayment_Amount_ContinueEnabled();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("IF there is error message: {0}, status of the button 'Continue' should be disabled")
    private void MakePayment_Amount_ContinueDisabled(String error) throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: 'Continue' Button should disabled after Amount error message" + error + "showed");
    }

    @Step("IF there is no error message, status of the button 'Continue' should be enabled")
    private void MakePayment_Amount_ContinueEnabled() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: 'Continue' Button should enabled if no error message showed ... PASS");
    }

    @Step("5. Input special sign in the amount field")
    private void Payment_SpecialSign5() throws InterruptedException
    {
        saveTextLog_Allure_er("Amount text field cannot be typed with special sign");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("$2,000.01").clear(); //clear Amount number
        iosDriver.findElementById("0.00").sendKeys("100~!@#$%^&*"); //Special Sign

        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById("1.00");
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: The special signs should not put in the Amount field ... PASS");
                MakePayment_Amount_SpecialSign_status2();
                MakePayment_Amount_SpecialSign2();
            } else {
                System.out.println("INFO: The special signs should not put in the Amount field ... FAIL");
                saveTextLog_Allure("ALLURE: The special signs should not be put in the Amount field ... FAIL");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.assertTrue(error_m.size() > 0);
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("Special Sign Case: Test after Click 'Next'")
    private void MakePayment_Amount_SpecialSign2() throws InterruptedException
    {
        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<IOSElement> button_next = iosDriver.findElementsById("Next");
        button_next.get(0).click();

        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById("$1.00");
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: Amount should not include the special signs when user click next ... PASS");
                MakePayment_Amount_SpecialSign_status2();
            } else {
                System.out.println("INFO: Amount should not include the special signs when user click next ... FAIL");
                saveTextLog_Allure("ALLURE: Amount should not include the special signs when user click next ... FAIL");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("If amount textField has no special sign, the status of the button 'Continue' should be enabled")
    private void MakePayment_Amount_SpecialSign_status2()
    {
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: 'Continue' Should be disable when special signs in the amount field ... Passed");
    }

    // Cancel
    @Step("6. Tap Cancel - End Test")
    private void Payment_Cancel6() throws InterruptedException
    {
        saveTextLog_Allure_er("MainPage is displayed");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Cancel").click();
        Thread.sleep(3000);
    }
}
