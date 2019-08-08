package SPRCOM_92052_Android_Sales;

import Android_Base.MainBase;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92052 My Sprint App Android - Sales")
@Feature("SPRCOM-105900 My Sprint App Android - LoginAO Sales")
public class SPRCOM_105900_Android_LoginAO_Sales_MainPage_UpgradeAO_Cart extends MainBase {

    public Float Monthly;
    public Float Today;

    @Test(groups = {"LoginAO_Sales"}, priority = 5, dataProvider = "DeviceOption",
            dataProviderClass = Data.SPRCOM_107202_DeviceOptions_Upgrade.class)
    @Description("My Sprint app Login - Sales: 'upgrade this device' on the MainPage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105906 My Sprint App iOS - MainPage: Upgrade Device and save to Cart")
    public void SPRCOM_105906(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption, String CurrentPhone) throws Exception
    {
        SPRCOM_105906_Step1();
        SPRCOM_105906_Step2(model);
        SPRCOM_105906_Step3();
        SPRCOM_105906_Step4(planOption);
        SPRCOM_105906_Step5(CurrentPhone);
        SPRCOM_105906_Step6(phonePlan);

    }

    @Step("1. Make sure MainPage loaded and Tap ‘Upgrade this device’")
    private void SPRCOM_105906_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Bottom Sheet should pop up with different type of devices");
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementByAndroidUIAutomator("text(\"Upgrade this device\")").click();
            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }
    
    @Step("2. Default filter, tap the model name on the page")
    private void SPRCOM_105906_Step2(String model)
    {
        saveTextLog_Allure_er("Device Details page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Error\")");
        try {
            if(error_m.size() > 0) {
                System.out.println("ERROR: error message");
                saveScreenshotPNG_Allure_Fail(Android_Driver);
                Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
                Assert.fail();
            } else {
                System.out.println("Tap phone model to next page");
                Android_Driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                Android_Driver.findElementByAndroidUIAutomator("text(\"" + model + "\")").click();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("3. Make sure Continue button is enabled and tap it")
    private void SPRCOM_105906_Step3()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").isEnabled());
        System.out.println("PASS: Continue - Default status is enabled");
        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }

    @Step("4. Use Data Provider with three options and tap continue")
    private void SPRCOM_105906_Step4(String planOption) throws Exception
    {
        saveTextLog_Allure_er("Plans page is displayed");

        String full_planOption;
        if(planOption.equals("Lease")) {
            full_planOption = "Sprint Flex 18-mo. lease";
        } else if(planOption.equals("Buy")) {
            full_planOption = "Buy it at full price";
        } else {
            full_planOption = "Buy it with 24 monthly installments";
        }

        Android_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"" + full_planOption + "\")").click();

        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }


    @Step("5. Tap the option of current handling")
    private void SPRCOM_105906_Step5(String currentPhone) throws Exception
    {
        saveTextLog_Allure_er("Next page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"" + currentPhone + "\")").click();

        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }

    @Step("6. Unknown Experience - Error - Back to main page")
    private void SPRCOM_105906_Step6(String planOption) throws Exception
    {
        saveTextLog_Allure_er("---Main page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> error_m = Android_Driver.findElementsByAndroidUIAutomator("text(\"Error\")");
        try {
            if(error_m.size() > 0) {
                System.out.println("ERROR: error message");
                saveScreenshotPNG_Allure_Fail(Android_Driver);
                Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();

                Android_Driver.navigate().back();
                Android_Driver.navigate().back();
                Android_Driver.navigate().back();
                Android_Driver.navigate().back();
                Android_Driver.navigate().back();

            } else {
                System.out.println("Unknown Experience");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }
}
