package SPRCOM_92078_iOS_Payment;

import iOS_Tests.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
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
@Epic("SPRCOM-92078 My Sprint App iOS - Payment")
@Feature("SPRCOM-106800 My Sprint App iOS - LoginAO Payment")
public class SPRCOM_106800_iOS_LoginAO_MakePayment extends MainBase {

    private String dueAmount;

    @Test(groups = {"Login", "AccountOwner"}, priority = 2)
    @Description("My Sprint App iOS LoginAO Payment - Phase 2 - Basic Payment Functional Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-106804 Basic Payment Functional Tests")
    public void SPRCOM_106804() throws InterruptedException
    {
        Button_Payment1();
        LoginAO_payment_amount2();
        LoginAO_payment_amount3();
        LoginAO_payment_SpecialSign4();
        LoginAO_payment_cancel5();
        LoginAO_payment_cancel6();
    }

    @Step("1. Click Button to the payment page and verify default status")
    private void Button_Payment1()
    {
        saveTextLog_Allure_er("Click Button 'Make a payment' and verify default status of button 'Next'");
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> texrfields = iosDriver.findElementsByXPath("//XCUIElementTypeTextField");
        dueAmount = texrfields.get(0).getText();
        if(dueAmount.equals("$0.00")) {
            iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Assert.assertFalse(iosDriver.findElementByAccessibilityId("Next").isEnabled());
            System.out.println("PASS: button 'Next' - Default status is disabled");
        } else {
            iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Assert.assertTrue(iosDriver.findElementByAccessibilityId("Next").isEnabled());
            System.out.println("PASS: button 'Next' - Default status is enabled");
        }
    }

    @Step("2. LoginAO Payment page - Amount cases: Less than $1")
    private void LoginAO_payment_amount2() throws InterruptedException
    {
        saveTextLog_Allure_er("Expected result XXX");
        // Step1
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(dueAmount).clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("99"); //Less than 1
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
                Assert.fail();
                MakePayment_Amount_ContinueEnabled();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("3. LoginAO Payment page - Amount cases: More than $2000.")
    public void LoginAO_payment_amount3() throws InterruptedException
    {
        saveTextLog_Allure_er("Expected result XXX");
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
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Next").isEnabled());
        System.out.println("INFO: 'Continue' Button should disabled after Amount error message" + error + "showed");
    }

    @Step("IF there is no error message, status of the button 'Next' should be enabled")
    private void MakePayment_Amount_ContinueEnabled() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Next").isEnabled());
        System.out.println("INFO: 'Continue' Button should enabled if no error message showed ... PASS");
    }

    @Step("4. LoginAO Payment page - Test with special sign on the page")
    private void LoginAO_payment_SpecialSign4() throws InterruptedException
    {
        saveTextLog_Allure_er("Expected result XXX");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("$2,000.01").clear(); //clear Amount number
        iosDriver.findElementById("0.00").sendKeys("100~!@#$%^&*"); //Special Sign

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById("1.00");
        try {
            if(error_m.size() > 0) {

                Thread.sleep(500);
                iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                List<IOSElement> button_next = iosDriver.findElementsById("Next");
                button_next.get(0).click();

                System.out.println("PASS: The special signs should not put in the Amount field");
                MakePayment_Amount_SpecialSign_status1();
            } else {
                System.out.println("FAIL: The special signs should not put in the Amount field");
                saveTextLog_Allure("FAIL: The special signs should not be put in the Amount field");
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("If amount textField has no special sign, the status of the button 'Next' should be enabled")
    private void MakePayment_Amount_SpecialSign_status1()
    {
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Next").isEnabled());
        System.out.println("PASS: 'Continue' Should be disable when special signs in the amount field");
    }

    @Step("5. LoginAO Payment page - Button Cancel - Unit")
    private void LoginAO_payment_cancel5()
    {
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Cancel").click();

        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Make a payment");
        try {
            if(payment_m.size() > 0) {
                System.out.println("PASS: Cancel the payment to the Main page");
            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("6. LoginAO Payment page - Complete Payment")
    private void LoginAO_payment_cancel6() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Make a payment").click();

        List<IOSElement> texrfields = iosDriver.findElementsByXPath("//XCUIElementTypeTextField");
        dueAmount = texrfields.get(0).getText();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(dueAmount).clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("100"); //Less than 1

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> button_next = iosDriver.findElementsById("Next");
        button_next.get(0).click();
        button_next.get(1).click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> Warning_m = iosDriver.findElementsById("Continue");
            try {
                if(Warning_m.size() > 0) {
                    System.out.println("INFO: Warning - 'The amount you entered is more than your current balance...'");
                    saveTextLog_Allure("Warning: 'The amount you entered is more than your current balance...'");

                    iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    iosDriver.findElementById("Continue").click();
                    iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    iosDriver.findElementById("Authorize").click();

                    iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                    List<IOSElement> Warning_m1 = iosDriver.findElementsById("Not Now");
                        try {
                            if(Warning_m1.size() > 0) {
                                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                                iosDriver.findElementById("Not Now").click();
                                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                                iosDriver.findElementById("OK").click();
                            } else {
                                // Please do not attempt to resubmit, as it may result in duplicate transaction
                                System.out.println("INFO: 'Please do not attempt to resubmit, as it may " +
                                        "result in duplicate transaction'");
                                saveTextLog_Allure("INFO: 'Please do not attempt to resubmit, as it may " +
                                        "result in duplicate transaction'");
                                saveScreenshotPNG_Allure(iosDriver);
                                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                                iosDriver.findElementById("OK").click();
                                Thread.sleep(2000);
                            }
                        }catch (NoSuchElementException e) {
                            System.out.println("Error: No such element found!");
                            Assert.fail();
                        }
                } else {
                    System.out.println("Unknown Experiences ......");
                    saveTextLog_Allure("Unknown Experiences ......");
                    // =============================================
                    Assert.fail();
                }
            }catch (NoSuchElementException e) {
                System.out.println("Error: No such element found!");
            }
    }

    @Test(groups = {"Login", "AccountOwner"}, priority = 9)
    @Description("LoginAO ApplePay - Default Payment")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-106806 LoginAO ApplePay - Default Payment")
    public void SPRCOM_106806() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Make a payment").click();

        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<IOSElement> texrfields = iosDriver.findElementsByXPath("//XCUIElementTypeTextField");
        dueAmount = texrfields.get(0).getText();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(dueAmount).clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("100");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Payment method").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Apple Pay").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> button_next = iosDriver.findElementsById("Next");
        button_next.get(0).click();

        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> Warning_m = iosDriver.findElementsById("Continue");
        try {
            if(Warning_m.size() > 0) {
                System.out.println("INFO: Warning - 'The amount you entered is more than your current balance...'");
                saveTextLog_Allure("Warning: 'The amount you entered is more than your current balance...'");

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Continue").click();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Pay with Passcode").click();

                iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                List<IOSElement> Warning_m1 = iosDriver.findElementsById("OK");
                try {
                    if(Warning_m1.size() > 0) {
                        // Please do not attempt to resubmit, as it may result in duplicate transaction
                        System.out.println("INFO: Error - 'Please verify the information provided is correct and " +
                                "try again'");
                        saveTextLog_Allure("Error: 'Please verify the information provided is correct and try again'");
                        saveScreenshotPNG_Allure(iosDriver);
                        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        iosDriver.findElementById("OK").click();
                        Thread.sleep(1000);
                        Assert.fail();
                    } else {
                        System.out.println("Unknown Experiences ......");
                        saveTextLog_Allure("INFO: Unknown Experiences ......");
                        Assert.fail();
                    }
                }catch (NoSuchElementException e) {
                    System.out.println("Error: No such element found!");
                }
            } else {
                System.out.println("Unknown Experiences ......");
                saveTextLog_Allure("INFO: Unknown Experiences ......");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Test(groups = {"Login", "AccountOwner"}, priority = 10)
    @Description("LoginAO Add New Card to account - from Payment page")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-106808 LoginAO Add New Card to account - from payment page")
    public void SPRCOM_106808() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Make a payment").click();

        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<IOSElement> texrfields = iosDriver.findElementsByXPath("//XCUIElementTypeTextField");
        dueAmount = texrfields.get(0).getText();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(dueAmount).clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("100");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Payment method").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Add payment method").click();

        //Camera allow if at first launch
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> camera_m =iosDriver.findElementsById("Don't Allow");
        try {
            if(camera_m.size() > 0) {
                System.out.println("INFO: Camera Already been allowed.");
                saveTextLog_Allure("INFO: Camera Already been allowed.");
            } else {
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();
                System.out.println("INFO: Allow Camera accessing ...");
                saveTextLog_Allure("INFO: Allow Camera accessing ...");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Name on card").sendKeys("Max");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Card number").sendKeys("4055011111111111");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Expiration date").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("July").sendKeys("December");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("2019").sendKeys("2021");
        iosDriver.findElementById("Done").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("ZIP").sendKeys("66223");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("CVV number").sendKeys("000");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Done").click();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Yes").click();

        //LongPress, duration and release -- Scroll down - with elements ID selected
        WebElement scroll_point1 = iosDriver.findElementById("Yes");
        WebElement scroll_point2 = iosDriver.findElementById("Checking");
        TouchAction t = new TouchAction(iosDriver);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Save").click();

        // ========= Card Already Added +++++++++++++
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> card_m =iosDriver.findElementsById("OK");
        try {
            if(card_m.size() > 0) {
                System.out.println("INFO: Card Already Added.");
                saveTextLog_Allure("INFO: Card Already Added.");
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("OK").click();
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel").click();
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel").click();
            } else {
                iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel").click();
                Thread.sleep(2000);
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
        // ==========================================


    }

    @Test(groups = {"Login", "AccountOwner"}, priority = 11)
    @Description("LoginAO Add New Checking to account - from Payment page")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-106812 LoginAO Add New Checking to account - from payment page")
    public void SPRCOM_106812() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Make a payment").click();

        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(dueAmount).clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("0.00").sendKeys("100");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Payment method").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Add payment method").click();

        //Camera allow if at first launch
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> camera_m =iosDriver.findElementsById("Don't Allow");
        try {
            if(camera_m.size() > 0) {
                System.out.println("INFO: Camera Already been allowed.");
                saveTextLog_Allure("INFO: Camera Already been allowed.");
            } else {
                iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();
                System.out.println("INFO: Allow Camera accessing ...");
                saveTextLog_Allure("INFO: Allow Camera accessing ...");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Routing number").sendKeys("xxx");
        iosDriver.findElementById("Account number").sendKeys("xxx");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Next").click();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Done").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Save").click();

        // ========= Checking account Already Added +++++++++++++
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Cancel").click();
        Thread.sleep(3000);
    }
}
