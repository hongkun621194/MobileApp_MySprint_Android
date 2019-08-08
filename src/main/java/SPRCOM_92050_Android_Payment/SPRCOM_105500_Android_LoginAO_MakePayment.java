package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.qameta.allure.*;
import org.openqa.selenium.InvalidElementStateException;
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
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105500 My Sprint App Android - LoginAO Payment")
public class SPRCOM_105500_Android_LoginAO_MakePayment extends MainBase {

    @Test(groups = {"LoginAO_Payment"}, priority = 2)
    @Description("My Sprint App Android LoginAO Payment - Phase 2 - Basic Payment Functional Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105504 Basic Payment Functional Tests")
    public void SPRCOM_105504() throws Exception
    {
        SPRCOM_105504_Step1();
        SPRCOM_105504_Step2();
        SPRCOM_105504_Step3();
//        SPRCOM_105504_Step4(); // Cancel special sign test
        SPRCOM_105504_Step5();
        SPRCOM_105504_Step6();
        SPRCOM_105504_Step7();
        SPRCOM_105504_Step8();
    }

    @Step("1. Click Button to the payment page and verify default status")
    private void SPRCOM_105504_Step1() throws Exception
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/action_button").click();
                System.out.println("INFO: Click Button 'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String dueAmount = Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").getText();
        if(dueAmount.equals("$0.00")) {
            Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
            System.out.println("PASS: button 'CONTINUE' - Default status is disabled");
        } else {
            Thread.sleep(3000);
            Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
            System.out.println("PASS: button 'CONTINUE' - Default status is enabled");
        }
    }

    @Step("2. Input amount less than $1")
    private void SPRCOM_105504_Step2() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Min. payment amount limit $1' should show up");
        // Step1
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").clear(); //clear Amount number
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("99"); //Less than 1
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").click();

        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsById("com.sprint.care.beta:id/textinput_error");
        String error_message = "Minimum payment - $1.00";
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: Amount less than $1 - Error Message " + error_message +
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

    @Step("3. Input amount more than $2000")
    private void SPRCOM_105504_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message 'Maximum payment amount limit $2000' should show up");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").clear(); //clear Amount number
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("200001"); //More than $2000
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").click();

        Thread.sleep(1000);
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

    @Step("IF there is error message: {0}, status of the button 'Next' should be disabled")
    private void MakePayment_Amount_ContinueDisabled(String error) throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
        System.out.println("INFO: 'Continue' Button should disabled after Amount error message" + error + "showed");
    }

    @Step("IF there is no error message, status of the button 'Next' should be enabled")
    private void MakePayment_Amount_ContinueEnabled() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").isEnabled());
        System.out.println("INFO: 'Continue' Button should enabled if no error message showed ... PASS");
    }

    @Step("4. Input special sign in the amount field")
    private void SPRCOM_105504_Step4() throws Exception
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

    @Step("5. Tap 'Continue' with the default payment method")
    private void SPRCOM_105504_Step5() throws Exception
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").clear(); //clear Amount number
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("100"); //More than $2000
        Thread.sleep(3000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").click();

        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Warning\")");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            } else {
                System.out.println("Unknown experiences");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("6. Tap 'AUTHORIZE' to continue")
    private void SPRCOM_105504_Step6() throws InterruptedException
    {
        saveTextLog_Allure_er("Payment confirmation dialog is displayed");
        Thread.sleep(500);
        Android_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/positive_btn");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            } else {
                System.out.println("Unknown experiences");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("7. Tap 'OK' to continue")
    private void SPRCOM_105504_Step7() throws InterruptedException
    {
        saveTextLog_Allure_er("Payment confirmation dialog is displayed");
        Thread.sleep(500);
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Payment confirmation\")");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            } else {
                if(Android_Driver.findElementById("com.sprint.care.beta:id/dialogTitle_tv").getText().equals("Error")){
                    Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
                    Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    Android_Driver.findElementById("com.sprint.care.beta:id/paymentCancel_button").click();
                }
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("8. Tap 'NO, THANKS' for the rate page")
    private void SPRCOM_105504_Step8() throws InterruptedException
    {
        saveTextLog_Allure_er("Main page is displayed");
        Thread.sleep(500);
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Rate My Sprint\")");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/negative_btn").click();
            } else {
                System.out.println("No rate dialog is displayed");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Test(groups = {"LoginAO_Payment"}, priority = 3)
    @Description("LoginAO Add New Card to account - from Payment page")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105506 LoginAO Add New Card to account - from payment page")
    public void SPRCOM_105506() throws InterruptedException
    {
        SPRCOM_105506_Step1();
        SPRCOM_105506_Step2();
        SPRCOM_105506_Step3();
        SPRCOM_105506_Step4();
        SPRCOM_105506_Step5();
    }

    @Step("1. Tap 'Make a payment' on the main page")
    private void SPRCOM_105506_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if (pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/action_button").click();
                System.out.println("INFO: Click Button 'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

    }

    @Step("2. Tap 'Payment method'")
    private void SPRCOM_105506_Step2() throws InterruptedException {
        saveTextLog_Allure_er("Payment method dialog box is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentMethod_et").click();
    }

    @Step("3. Tap 'Add a new payment method' and press 'OK'")
    private void SPRCOM_105506_Step3() throws InterruptedException {
        saveTextLog_Allure_er("Add payment method page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/selector_rb").click();
        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
    }

    @Step("4. Enter the card info on the page")
    private void SPRCOM_105506_Step4() throws InterruptedException
    {
        saveTextLog_Allure_er("Card info is entered and 'Continue' status is enabled");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/tvNameOnCard").sendKeys("Max");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/cardNumberTextField").sendKeys("4055011111111111");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/expirationDateTextField").click();

        Thread.sleep(500);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/ok_button").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/zipCodeTextField").sendKeys("66223");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeTextField").sendKeys("000");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/poof_rb_yes").click();

//        Android_Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Max\"));");
        //LongPress, duration and release -- Scroll down - with elements ID selected
        WebElement scroll_point1 = Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeTextField");
        WebElement scroll_point2 = Android_Driver.findElementById("com.sprint.care.beta:id/tvNameOnCard");
        TouchAction t = new TouchAction(Android_Driver);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();

        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/save").isEnabled());
        System.out.println("PASS: button 'CONTINUE' status is enabled");
    }

    @Step("5. Tap 'Continue'")
    private void SPRCOM_105506_Step5() throws InterruptedException
    {
        saveTextLog_Allure_er("Make a payment page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/save").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Warning\")");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            } else {
                System.out.println("No warning message poped up");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> card_m =Android_Driver.findElementsById("com.sprint.care.beta:id/billAmount_tv");
        try {
            if(card_m.size() > 0) {
                System.out.println("INFO: Card Added");
                saveTextLog_Allure("INFO: Card Added");
                Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/paymentCancel_button").click();
            } else {
                System.out.println("Unknown experience");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Test(groups = {"LoginAO_Payment"}, priority = 4)
    @Description("LoginAO Add New Checking to account - from Payment page")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105508 LoginAO Add New Checking to account - from payment page")
    public void SPRCOM_105508() throws InterruptedException
    {
        SPRCOM_105508_Step1();
        SPRCOM_105508_Step2();
        SPRCOM_105508_Step3();
        SPRCOM_105508_Step4();
        SPRCOM_105508_Step5();
    }

    @Step("1. Tap 'Make a payment' on the main page")
    private void SPRCOM_105508_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if (pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/action_button").click();
                System.out.println("INFO: Click Button 'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

    }

    @Step("2. Tap 'Payment method'")
    private void SPRCOM_105508_Step2() throws InterruptedException {
        saveTextLog_Allure_er("Payment method dialog box is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentMethod_et").click();
    }

    @Step("3. Tap 'Add a new payment method' and press 'OK'")
    private void SPRCOM_105508_Step3() throws InterruptedException {
        saveTextLog_Allure_er("Add payment method page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/selector_rb").click();
        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
    }

    @Step("4. Tap 'Checking' and enter the checking info on the page")
    private void SPRCOM_105508_Step4() throws InterruptedException
    {
        saveTextLog_Allure_er("Checking info entered and 'Continue' status is enabled");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementByXPath("//*[@content-desc='Checking']").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/routingNumberTextField").sendKeys("0000");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/accountNumberTextField").sendKeys("0000");

        Thread.sleep(3000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertFalse(Android_Driver.findElementById("com.sprint.care.beta:id/save").isEnabled());
        System.out.println("PASS: button 'CONTINUE' status is not enabled");
    }

    @Step("5. Tap 'Cancel' and tap cancel to main page")
    private void SPRCOM_105508_Step5() throws InterruptedException
    {
        saveTextLog_Allure_er("Make a payment page is displayed");
//        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        Android_Driver.findElementById("com.sprint.care.beta:id/save").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/cancel").click();

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Warning\")");
//        List<AndroidElement> pay_m = Android_Driver.findElementsById("Make a payment");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            } else {
                System.out.println("No warning message poped up");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> card_m =Android_Driver.findElementsById("com.sprint.care.beta:id/billAmount_tv");
        try {
            if(card_m.size() > 0) {
                System.out.println("INFO: Card Added");
                saveTextLog_Allure("INFO: Card Added");
                Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/paymentCancel_button").click();
            } else {
                System.out.println("Unknown experience");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }
}
