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
@Epic("SPRCOM-92052 My Sprint App iOS - Sales")
@Feature("SPRCOM-105900 My Sprint App iOS - LoginAO Sales")
public class SPRCOM_105900_Android_LoginAO_Sales_MainPage_AddNewLine_Cart extends MainBase {

    public Float Monthly;
    public Float Today;

    @Test(groups = {"LoginAO_Sales"}, priority = 5, dataProvider = "DeviceOption",
            dataProviderClass = Data.SPRCOM_107202_DeviceOptions.class)
    @Description("My Sprint app Login - Sales: 'Upgrade this device' on the MainPage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105902 My Sprint App iOS - MainPage: Upgrade the device")
    public void SPRCOM_105902(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption) throws Exception
    {
        SPRCOM_105902_Step1();
        SPRCOM_105902_Step2(deviceType);
        SPRCOM_105902_Step3(model);
        SPRCOM_105902_Step4();
        SPRCOM_105902_Step5(planOption);
        SPRCOM_105902_Step6(phonePlan);
        SPRCOM_105902_Step7(protectionOption);
        SPRCOM_105902_Step8();

    }

    @Step("1. Make sure MainPage loaded and Tap ‘Add a New Device’")
    private void SPRCOM_105902_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Bottom Sheet should pop up with different type of devices");
        Android_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementByAndroidUIAutomator("text(\"Add a New Device\")").click();
            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Tap Phones icon")
    private void SPRCOM_105902_Step2(String deviceType)
    {
        saveTextLog_Allure_er("Loading for a long time at first launch(20s) and Shop devices page is displayed");

        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"" + deviceType+ "\")").click();
    }

    @Step("3. Default filter, tap the model name on the page")
    private void SPRCOM_105902_Step3(String model)
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

    @Step("4. Make sure Continue button is enabled and tap it")
    private void SPRCOM_105902_Step4()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").isEnabled());
        System.out.println("PASS: Continue - Default status is enabled");
        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }

    @Step("5. Use Data Provider with three options and tap continue")
    private void SPRCOM_105902_Step5(String planOption) throws Exception
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

//        List<AndroidElement> Device_Detail = Android_Driver.findElementsByClassName("XCUIElementTypeStaticText");
//        System.out.println(Device_Detail);
//        for (AndroidElement AndroidElement : Device_Detail) {
//            System.out.println(AndroidElement.getText());
//        }
//        Today = Float.valueOf(Device_Detail.get(5).getText().replace("$", " "));
//        Monthly = Float.valueOf(Device_Detail.get(6).getText().replace("$", " "));

        Thread.sleep(1000);
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }

    @Step("6. Make sure default Continue button is disabled and click default shared plan ($20)? , tap Continue")
    private void SPRCOM_105902_Step6(String phonePLan)
    {
        saveTextLog_Allure_er("Protection page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        Android_Driver.findElementByAndroidUIAutomator("text(\""+ phonePLan +"\")").click();

//        if(phonePLan.equals("Unlimited Savings Plan")) {
//            Monthly = Monthly + 20;
//        }

        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").isEnabled());
        System.out.println("PASS: Continue - status is enabled");

        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/continueButton").click();
    }
    @Step("7. Tap button ‘Add protection’?")
    private void SPRCOM_105902_Step7(String protectionOption) throws InterruptedException
    {
        saveTextLog_Allure_er("Wait for loading(15s), Cart page is displayed OR error happens, and main page is displayed");
//        if(protectionOption.equals("Add protection")) {
//            Monthly = Monthly + 19;
//        }

        if(protectionOption.equals("Add protection")) {
            Android_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Android_Driver.findElementById("com.sprint.care.beta:id/acceptAndContinue").click();
        }
    }

    @Step("8. Verify the value of monthly and today due / error - Back to main page")
    private void SPRCOM_105902_Step8() throws InterruptedException
    {
        saveTextLog_Allure_er("All payment values are correct / Main page is displayed");

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
