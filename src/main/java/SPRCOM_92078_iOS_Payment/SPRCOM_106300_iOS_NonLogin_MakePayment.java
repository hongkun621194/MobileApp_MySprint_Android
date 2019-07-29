package SPRCOM_92078_iOS_Payment;

import iOS_Tests.MainBase;
import io.appium.java_client.ios.IOSElement;
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
public class SPRCOM_106300_iOS_NonLogin_MakePayment extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 1)
    @Description("Basic Payment Functions - icon, button, card info")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-106306 Payment making with credit/debit card")
    public void SPRCOM_106302() throws Exception
    {
//        String TestCase_id = "SPRCOM_106302";
//        iosDriver.startRecordingScreen(IOSStartScreenRecordingOptions.startScreenRecordingOptions().withVideoType("h264"));
        SPRCOM_106302_Step1();
        SPRCOM_106302_Step2();
        SPRCOM_106302_Step3();
        SPRCOM_106302_Step4();
        SPRCOM_106302_Step5();
        SPRCOM_106302_Step6();
        SPRCOM_106302_Step7();
        SPRCOM_106302_Step8();
        SPRCOM_106302_Step9();
        SPRCOM_106302_Step10();
        SPRCOM_106302_Step11();

//        String Video_c = iosDriver.stopRecordingScreen();
//        String pathName = "videoClips/" + TestCase_id + ".mp4";
//        Path destinationFile = Paths.get(pathName);
//        Files.write(destinationFile, Base64.decodeBase64(Video_c));
//        attach_FilePathVideo(pathName);
//        saveH264_Allure_Video(Video_c);
    }

    @Step("1. Click Button and select 'Pay with Debit/Credit Card'")
    private void SPRCOM_106302_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
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

        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<IOSElement> camera_m =iosDriver.findElementsById("Pay with Debit/Credit Card");
        try {
            if(camera_m.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                iosDriver.findElementById("Pay with Debit/Credit Card").click();
                System.out.println("INFO: Click Button and select 'Pay with Debit/Credit Card'");
            } else {
                System.out.println("INFO: No 'Pay with Debit/Credit Card' option ... PASS");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Allow Camera access at the payment page'")
    private void SPRCOM_106302_Step2()
    {
        saveTextLog_Allure_er("Camera is allowed");
        // Allow Camera if first launch
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<IOSElement> camera_m =iosDriver.findElementsById("Don't Allow");
        try {
            if(camera_m.size() > 0) {
                System.out.println("INFO: Camera Already been allowed.");
                saveTextLog_Allure("Allure_INFO: Camera Already been allowed.");
            } else {
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();
                System.out.println("INFO: Allow Camera accessing ...");
                saveTextLog_Allure("Allure_INFO: Allow Camera accessing ...");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("3. Payment page 'more info' icon functional test")
    private void SPRCOM_106302_Step3() throws InterruptedException
    {
        saveTextLog_Allure_er("icon worked and info page is displayed and can be return to payment page");
        // More info icon click
        iosDriver.findElementByAccessibilityId("More info").click();
        Thread.sleep(1000);
        iosDriver.navigate().back(); //Navigate back
        iosDriver.findElementByAccessibilityId("More info").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("OK").click(); //Click Button 'OK' to get back

        System.out.println("Info: More Info Icon Click - Done");
    }

    @Step("4. check default status of button 'Continue'")
    private void SPRCOM_106302_Step4()
    {
        saveTextLog_Allure_er("The button default status should be disabled");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue - Default status is disabled ... Passed");
    }

    @Step("5. verify status of button 'Continue' after payment info filled")
    private void SPRCOM_106302_Step5()
    {
        saveTextLog_Allure_er("Fill the correct payment info and make sure 'Continue' button status is enabled");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("$0.00").sendKeys("100");
        iosDriver.findElementById("Name on card").sendKeys("Max");
        iosDriver.findElementById("Card number").sendKeys("4055011111111111");
        iosDriver.findElementById("Expiration date").click();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("July").sendKeys("December");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("2019").sendKeys("2021");
        iosDriver.findElementById("Done").click();
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("ZIP").sendKeys("66223");
        iosDriver.findElementById("CVV number").sendKeys("000");
        iosDriver.findElementById("Done").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Fill card information to test the 'Continue' button is displayed ... Passed");
    }

    // =======================================
    // Amount part tests
    @Step("6. input amount less than $1")
    private void SPRCOM_106302_Step6() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Min. payment amount limit $1' should show up");
        // Step1
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("$1.00").clear();
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

    @Step("7. input amount more than $2000")
    private void SPRCOM_106302_Step7() throws InterruptedException
    {
        saveTextLog_Allure_er("The error message '- Max. payment amount limit $2000' should show up");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("$0.99").clear(); //clear Amount number
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

    @Step("8. Input special sign in the amount field")
    private void SPRCOM_106302_Step8() throws InterruptedException
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
                System.out.println("FAIL: The special signs should not put in the Amount field");
                saveTextLog_Allure("FAIL: The special signs should not be put in the Amount field");
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

    // =======================================
    // Name part test
    @Step("9. Test with name text filed on the page")
    private void SPRCOM_106302_Step9() throws InterruptedException
    {
        saveTextLog_Allure_er("The Continue button status will change");
        //Step1
        MakePayment_Name_Empty();
        //Step2
        MakePayment_Name_FillAgain();
    }

    @Step("Change name field to empty, check the button 'Continue' status")
    private void MakePayment_Name_Empty() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("Max").clear();
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue button should be disabled when the name field is empty ... PASS");
    }

    @Step("Fill name field again, check the button 'Continue' status")
    private void MakePayment_Name_FillAgain() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("Name on card").sendKeys("Max");
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue button should be enabled when the name field is filled again ... PASS");
    }

    // Data picker tests (Later) false enabled

    // Zip code tests
    @Step("10. Payment page - Test with ZIP text filed on the page")
    @Description("Payment page - ZIP textField tests")
    private void SPRCOM_106302_Step10() throws InterruptedException
    {
        saveTextLog_Allure_er("The Continue button status will change");
        //Step1
        MakePayment_Zip_Empty();
        //Step2
        MakePayment_Zip_Less5Numbers1();
    }

    @Step("Change zip field to empty, check the button 'Continue' status")
    private void MakePayment_Zip_Empty() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("66223").clear();
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue button should be disabled when the name field is empty ... Passed");
    }

    @Step("file ZIP field less than 5 numbers, check the button 'Continue' status")
    private void MakePayment_Zip_Less5Numbers1() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("ZIP").sendKeys("6622"); // Less than 5 digits
        iosDriver.findElementById("Next").click();

        Thread.sleep(500);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<IOSElement> error_m = iosDriver.findElementsById("- Invalid ZIP code.");
        String error_message = "- Invalid ZIP code.";
        try {
            if(error_m.size() > 0) {
                System.out.println("INFO: Error message" + error_message + "should display when the ZIP code " +
                        "is less than 5 digits ... PASS");
                MakePayment_Zip_Less5Numbers_status1();
            } else {
                System.out.println("INFO: Error message" + error_message + "should display when the ZIP code " +
                        "is less than 5 digits ... PASS");
                saveTextLog_Allure("ALLURE: Error message" + error_message + "should display when the ZIP code " +
                        "is less than 5 digits ... PASS");
                MakePayment_Zip_Less5Numbers_status2();
                saveScreenshotPNG_Allure(iosDriver);
                iosDriver.navigate().back();
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("No elements found");
        }
    }

    @Step("if error message showed, Continue button should be disabled")
    private void MakePayment_Zip_Less5Numbers_status1() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue button should be disabled ... Passed");
    }

    @Step("if error message not showed, Continue button should be enabled")
    private void MakePayment_Zip_Less5Numbers_status2() throws InterruptedException
    {
        Thread.sleep(500);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("INFO: Continue button should be enabled ... PASS");
    }

    // Cancel
    @Step("11. Tap Cancel - End Test")
    private void SPRCOM_106302_Step11() throws InterruptedException
    {
        saveTextLog_Allure_er("MainPage is displayed");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("Cancel").click();
        Thread.sleep(3000);
    }
}
