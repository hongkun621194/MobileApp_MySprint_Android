package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.appium.java_client.android.AndroidElement;
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
@Feature("SPRCOM-105500 My Sprint App Android - LoginAO Payment")
public class SPRCOM_105500_Android_LoginAO_Login extends MainBase {

    @Test(groups = {"LoginAO_Payment"}, priority = 1)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105502 Test Login with type of account owner")
    public void SPRCOM_105502() throws Exception
    {
        SPRCOM_105502_Step1();
        SPRCOM_105502_Step2();
        SPRCOM_105502_Step3();
        SPRCOM_105502_Step4();
        SPRCOM_105502_Step5();
    }

    @Step("1. Verify the main page is displayed and tap 'more info' icon")
    private void SPRCOM_105502_Step1()
    {
        saveTextLog_Allure_er("Drop down sheet is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementsByClassName("android.widget.ImageView").get(0).click();


            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Tap 'Sign in'")
    private void SPRCOM_105502_Step2()
    {
        saveTextLog_Allure_er("Sign in page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator("text(\"Sign in\")").click();
    }


    @Step("3. Enter the username and password into the fields")
    private void SPRCOM_105502_Step3()
    {
        saveTextLog_Allure_er("Username and password are entered");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/userNameTextField").sendKeys("TWREG-51492");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/passwordTextField").sendKeys("T3stM3.P1s");
    }

    @Step("4. Tap 'Sign in' button")
    private void SPRCOM_105502_Step4()
    {
        saveTextLog_Allure_er("Sign in successfully");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Android_Driver.findElementById("com.sprint.care.beta:id/loginButton").click();
    }

    @Step("5. Verify the main page is displayed")
    private void SPRCOM_105502_Step5()
    {
        saveTextLog_Allure_er("Main page is displayed");
        Android_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        List<AndroidElement> payment_m = Android_Driver.findElementsById("com.sprint.care.beta:id/action_button");
        try {
            if(payment_m.size() < 1) {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
        System.out.println("INFO: Login Successfully \n -----------------------------------------------");
    }
}
