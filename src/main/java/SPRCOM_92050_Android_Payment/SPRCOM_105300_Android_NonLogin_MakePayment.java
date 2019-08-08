package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.openqa.selenium.InvalidElementStateException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105300 My Sprint App Android - NonLogin Payment")
public class SPRCOM_105300_Android_NonLogin_MakePayment extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 2)
    @Description("Basic Payment Functions - icon, button, card info")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105302 Payment making with basic function testing")
    public void SPRCOM_105802() throws Exception
    {
        SPRCOM_105802_Step1();
        SPRCOM_105802_Step2();
        SPRCOM_105802_Step3();
        SPRCOM_105802_Step4();
        SPRCOM_105802_Step5();
        SPRCOM_105802_Step6();
        SPRCOM_105802_Step7();
        SPRCOM_105802_Step8();
        SPRCOM_105802_Step9();
    }

    @Step("1. Tap the button 'Make a payment' on the MainPage")
    private void SPRCOM_105802_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//                Android_Driver.findElementById("Make a payment").click();
                Android_Driver.findElementByAndroidUIAutomator("text(\"Make a payment\")").click();

                System.out.println("INFO: Clicked the button'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Allow Camera access at the payment page'")
    private void SPRCOM_105802_Step2() throws Exception
    {
        saveTextLog_Allure_er("Camera is allowed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/cardNumberCardio");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/cardNumberCardio").click();

                // Allow Camera if first launch
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                List<AndroidElement> camera_m = Android_Driver.findElementsById("com.android.packageinstaller:id/permission_allow_button");
                try {
                    if(camera_m.size() == 0) {
                        System.out.println("INFO: Camera Already been allowed.");
                        saveTextLog_Allure("Allure_INFO: Camera Already been allowed.");

                        TouchAction t = new TouchAction(Android_Driver);
                        Thread.sleep(5000);
                        t.tap(PointOption.point(950, 1670)).release().perform();
                    } else {
                        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        Android_Driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();

                        TouchAction t = new TouchAction(Android_Driver);
                        Thread.sleep(5000);
                        t.tap(PointOption.point(950, 1670)).release().perform();

                        System.out.println("INFO: Allow Camera accessing ...");
                        saveTextLog_Allure("Allure_INFO: Allow Camera accessing ...");
                    }
                }catch (NoSuchElementException e) {
                    System.out.println("Error: No such element found!");
                }
            } else {
                System.out.println("FAIL: Page Changed");
                saveTextLog_Allure("FAIL: Page Changed");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("3. Payment page 'more info' icon functional test")
    private void SPRCOM_105802_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("icon worked and info dialog is displayed and can be return to payment page");
        // More info icon click
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeHelp").click();
        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"OK\")").click();
        System.out.println("Info: More Info Icon Click - Done");
    }

    @Step("4. check default status of button 'Continue'")
    private void SPRCOM_105802_Step4()
    {
        saveTextLog_Allure_er("The button default status should be disabled");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/continueBtn").isEnabled());
        System.out.println("INFO: Continue - Default status is disabled ... Passed");
    }

    @Step("5. verify status of button 'Continue' after payment info filled and tap 'Continue' ")
    private void SPRCOM_105802_Step5() throws Exception
    {
        saveTextLog_Allure_er("'Continue' button status is enabled and amount page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/tvNameOnCard").sendKeys("Max");
        Android_Driver.findElementById("com.sprint.care.beta:id/cardNumberTextField").sendKeys("4055011111111111");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/expirationDateTextField").click();
//        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Android_Driver.findElementById("July").sendKeys("December");
//        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Android_Driver.findElementById("2019").sendKeys("2021");
//        Android_Driver.findElementById("Done").click();
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/ok_button").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/zipCodeTextField").sendKeys("66223");
        Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeTextField").sendKeys("000");

        Thread.sleep(2000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/continueBtn").isEnabled());
        System.out.println("INFO: Fill card information to test the 'Continue' button is displayed ... Passed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueBtn").click();
    }

    // =======================================
    // Amount part tests
    @Step("6. Input amount less than $1")
    private void SPRCOM_105802_Step6() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Min. payment amount limit $1' should show up");
        // Step1
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("99");

        TouchAction t = new TouchAction(Android_Driver);
        Thread.sleep(500);
        t.tap(PointOption.point(450, 490)).release().perform();

        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsById("com.sprint.care.beta:id/textinput_error");
        String error_message = "Minimum payment - $1.00";
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: Amount less than $1 - Error Message 'Minimum payment - $1.00" +
                        "should show ... PASS");
                MakePayment_Amount_ContinueDisabled(error_message);

            } else {
                System.out.println("INFO: Amount less than $1 - Error Message" + error_message + "should show.");
                saveTextLog_Allure("Allure: Amount less than $1 - Error Message" + error_message + "should show.");
                Assert.fail();
                MakePayment_Amount_ContinueEnabled();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("7. input amount more than $2000")
    private void SPRCOM_105802_Step7() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message 'Maximum payment amount limit $2000' should show up");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").clear(); //clear Amount number
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("200001"); //More than $2000

        TouchAction t = new TouchAction(Android_Driver);
        Thread.sleep(500);
        t.tap(PointOption.point(450, 490)).release().perform();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsById("com.sprint.care.beta:id/textinput_error");
        String error_message = "Maximum payment amount limit $2000";
        try {
            if(error_m.size() > 0) {
                System.out.println("PASS: Amount more than $2000 - Error Message " + error_message +
                        "should show");
                MakePayment_Amount_ContinueDisabled(error_message);

            } else {
                System.out.println("FAIL: Amount more than $2000 - Error Message " + error_message +
                        "should show");
                saveTextLog_Allure("FAIL: Amount more than $2000 - Error Message " + error_message +
                        "should show");
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
        Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
        System.out.println("INFO: 'Continue' Button should disabled after Amount error message" + error + "showed");
    }

    @Step("IF there is no error message, status of the button 'Continue' should be enabled")
    private void MakePayment_Amount_ContinueEnabled() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
        System.out.println("INFO: 'Continue' Button should enabled if no error message showed ... PASS");
    }

    @Step("8. Input special sign in the amount field")
    private void SPRCOM_105802_Step8() throws Exception
    {
        saveTextLog_Allure_er("Amount text field cannot be typed with special sign");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").clear(); //clear Amount number

        Thread.sleep(1000);
        try {
            Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("100~!@#$%^&*"); //Special Sign
            Thread.sleep(500);
            Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            List<AndroidElement> error_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"1.00\")");
            try {
                if(error_m.size() > 0) {
                    System.out.println("INFO: The special signs should not put in the Amount field ... PASS");
                    MakePayment_Amount_SpecialSign_status2();
                } else {
                    System.out.println("FAIL: The special signs should not put in the Amount field");
                    saveTextLog_Allure("FAIL: The special signs should not be put in the Amount field");
                    saveScreenshotPNG_Allure(Android_Driver);
                    Android_Driver.navigate().back();
                    Assert.assertTrue(error_m.size() > 0);
                }
            }catch (NoSuchElementException e) {
                System.out.println("No elements found");
            }
        }catch (InvalidElementStateException e) {
            System.out.println("The special signs cannot put in the Amount field");
            saveTextLog_Allure("The special signs cannot put in the Amount field");
        }
    }

    @Step("If amount textField has no special sign, the status of the button 'Continue' should be enabled")
    private void MakePayment_Amount_SpecialSign_status2() throws Exception
    {
        Thread.sleep(2000);
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
        System.out.println("INFO: 'Continue' Should be disable when special signs in the amount field ... Passed");
    }


    // Cancel
    @Step("9. Tap Cancel - End Test")
    private void SPRCOM_105802_Step9() throws InterruptedException
    {
        saveTextLog_Allure_er("MainPage is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Make a payment\")");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                System.out.println("INFO: MainPage is displayed");
            } else {
                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Android_Driver.findElementByAccessibilityId("com.sprint.care.beta:id/paymentCancel_button").click();
                Thread.sleep(6000);
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

    }
}
