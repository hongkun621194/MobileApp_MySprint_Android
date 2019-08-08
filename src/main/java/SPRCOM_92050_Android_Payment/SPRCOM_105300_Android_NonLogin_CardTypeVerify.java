package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105300 My Sprint App Android - NonLogin Payment")
public class SPRCOM_105300_Android_NonLogin_CardTypeVerify extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 2, dataProvider = "CardNumber",
            dataProviderClass = Data.SPRCOM_106308_CardNumbers.class)
    @Description("Test the function of payment making with different types of card (Card Number Support)")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("SPRCOM-105304 Payment Test with card types(Number)")
    public void SPRCOM_105304(String cardNum, String cardType, String cardCountry) throws Exception
    {
        SPRCOM_105304_Step1();
        SPRCOM_105304_Step2(cardNum, cardType);
        SPRCOM_105304_Step3(cardNum, cardType, cardCountry);
        SPRCOM_105304_Step4();
        SPRCOM_105304_Step5();
        SPRCOM_105304_Step6(cardNum, cardType, cardCountry);
    }


    @Step("1. Tap the button 'Make a payment' on the MainPage")
    private void SPRCOM_105304_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> pay_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Make a payment\")");
        try {
            if(pay_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                Android_Driver.findElementByAndroidUIAutomator("text(\"Make a payment\")").click();
                System.out.println("INFO: Click Button'Make a payment'");
            } else {
                System.out.println("FAIL: connection failed - No 'Make a payment' button showed");
                saveTextLog_Allure("FAIL: connection failed - No 'Make a payment' button showed");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Fill the info of card with different card number from data provider")
    private void SPRCOM_105304_Step2(String cardNum, String cardType) throws Exception
    {
        saveTextLog_Allure_er("Card info is entered");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/tvNameOnCard").sendKeys("Max");
        Android_Driver.findElementById("com.sprint.care.beta:id/cardNumberTextField").sendKeys(cardNum);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/expirationDateTextField").click();
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/ok_button").click();
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/zipCodeTextField").sendKeys("66223");

        if (cardType.equals("American Express")) {
            Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeTextField").sendKeys("0000");
        } else {
            Android_Driver.findElementById("com.sprint.care.beta:id/securityCodeTextField").sendKeys("000");
        }
    }

    @Step("3. Check if there is error message '- Invalid card number.', and tap 'Continue'")
    private void SPRCOM_105304_Step3(String cardNum, String cardType, String cardCountry)
    {
        saveTextLog_Allure_er("Amount page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        List<AndroidElement> elements1 = Android_Driver.findElementsByAndroidUIAutomator("text(\"Card number is invalid\")");
        try {
            if(elements1.size() > 0) {
                System.out.println("INFO: Invalid card number. \n " + cardType + " - " + cardNum + " - " +
                        cardCountry + "... Failed");
                saveTextLog_Allure("Allure: Invalid card number! - FAIL");
                Android_Driver.findElementById("com.sprint.care.beta:id/cancelBtn").click();
                Assert.fail();
            } else if(Android_Driver.findElementById("com.sprint.care.beta:id/continueBtn").isEnabled()) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/continueBtn").click();

            } else {
                Android_Driver.findElementById("com.sprint.care.beta:id/cancelBtn").click();
                System.out.println("FAIL: Test Failed with unknown reason - Screenshot taken");
                saveTextLog_Allure("FAIL: Test Failed with unknown reason - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
    }

    @Step("4. Fill the amount text field and tap continue")
    private void SPRCOM_105304_Step4() throws Exception
    {
        saveTextLog_Allure_er("Payment result page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentAmount_et").sendKeys("100");
        TouchAction t = new TouchAction(Android_Driver);
        Thread.sleep(500);
        t.tap(PointOption.point(450, 490)).release().perform();

        Thread.sleep(3000);
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/paymentContinue_button").click();
    }

    @Step("5. Tap 'Authorize' button")
    private void SPRCOM_105304_Step5()
    {
        saveTextLog_Allure_er("Payment result dialog is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> error_m1 = Android_Driver.findElementsById("com.sprint.care.beta:id/positive_btn");
        try {
            if(error_m1.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
            }
            else {
                System.out.println("FAIL: Authorization Failed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: Authorization Failed with unknown reason! - Screenshot taken");

                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
//                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//                Android_Driver.findElementByAccessibilityId("Cancel").click();
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
    }

    @Step("6. Tap 'OK' to finish the payment")
    private void SPRCOM_105304_Step6(String cardNum, String cardType, String cardCountry)
    {
        saveTextLog_Allure_er("MainPage is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();

        System.out.println("INFO: The Card " + cardType + " - " + cardNum + " - " + cardCountry +
                " Passed Test");
    }


}
