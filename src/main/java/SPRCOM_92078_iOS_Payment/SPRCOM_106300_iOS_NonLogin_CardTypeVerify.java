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

import static Listeners_Tests.Listeners_Example.saveTextLog_Allure;
import static Listeners_Tests.Listeners_Example.saveTextLog_Allure_er;

@Listeners(Listeners_Tests.Listeners_Example.class)
@Epic("SPRCOM-92078 My Sprint App iOS - Payment")
@Feature("SPRCOM-106300 My Sprint App iOS - NonLogin Payment")
public class SPRCOM_106300_iOS_NonLogin_CardTypeVerify extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 2, dataProvider = "CardNumber",
            dataProviderClass = Data.SPRCOM_106308_CardNumbers.class)
    @Description("Test the function of payment making with different types of card (Card Number Support)")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("SPRCOM-106308 Payment Test with card types(Number)")
    public void SPRCOM_106304(String cardNum, String cardType, String cardCountry)
    {
        SPRCOM_106304_Step1();
        SPRCOM_106304_Step2(cardNum, cardType);
        SPRCOM_106304_Step3(cardNum, cardType, cardCountry);
        SPRCOM_106304_Step4();
        SPRCOM_106304_Step5(cardNum, cardType, cardCountry);
    }


    @Step("1. Click Button 'Make a payment' and select 'Pay with Debit/Credit Card'")
    private void SPRCOM_106304_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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
        iosDriver.findElementById("Pay with Debit/Credit Card").click();
    }

    @Step("2. Fill the info of card with different card number from data provider")
    private void SPRCOM_106304_Step2(String cardNum, String cardType)
    {
        saveTextLog_Allure_er("Card info is entered");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("$0.00").sendKeys("100");
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("Name on card").sendKeys("Max");
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("Card number").sendKeys(cardNum);
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("Expiration date").click();
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("July").sendKeys("December");
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("2019").sendKeys("2021");
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("Done").click();
        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        iosDriver.findElementById("ZIP").sendKeys("66223");
        if (cardType.equals("American Express")) {
            iosDriver.findElementById("CVV number").sendKeys("0000");
        } else {
            iosDriver.findElementById("CVV number").sendKeys("000");
        }
        iosDriver.findElementById("Done").click();
    }

    @Step("3. Check if there is error message '- Invalid card number.', and tap 'Continue'")
    private void SPRCOM_106304_Step3(String cardNum, String cardType, String cardCountry)
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");

        iosDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<IOSElement> elements1 = iosDriver.findElementsById("- Invalid card number.");
        try {
            if(elements1.size() > 0) {
                System.out.println("INFO: Invalid card number. \n " + cardType + " - " + cardNum + " - " +
                        cardCountry + "... Failed");
                saveTextLog_Allure("Allure: Invalid card number! - FAIL");
                iosDriver.findElementByAccessibilityId("Cancel").click();
                Assert.fail();
            } else if(iosDriver.findElementByAccessibilityId("Continue").isEnabled()) {
                iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Continue").click();

            } else {
                iosDriver.findElementByAccessibilityId("Cancel").click();
                System.out.println("FAIL: Test Failed with unknown reason - Screenshot taken");
                saveTextLog_Allure("FAIL: Test Failed with unknown reason - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }


    }

    @Step("4. Tap 'Authorize' button and tap 'Not now'")
    private void SPRCOM_106304_Step4()
    {
        saveTextLog_Allure_er("Payment result page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementById("Authorize").click();

        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<IOSElement> error_m1 = iosDriver.findElementsById("Not Now");
        try {
            if(error_m1.size() > 0) {
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Not Now").click();
            }
            else {
                System.out.println("FAIL: Authorization Failed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: Authorization Failed with unknown reason! - Screenshot taken");

                iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                iosDriver.findElementById("OK").click();
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Cancel").click();
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
    }

    @Step("5. Tap 'OK' to finish the payment")
    private void SPRCOM_106304_Step5(String cardNum, String cardType, String cardCountry)
    {
        saveTextLog_Allure_er("MainPage is displayed");

        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        iosDriver.findElementByAccessibilityId("OK").click();

        System.out.println("INFO: The Card " + cardType + " - " + cardNum + " - " + cardCountry +
                " Passed Test");
    }




}
