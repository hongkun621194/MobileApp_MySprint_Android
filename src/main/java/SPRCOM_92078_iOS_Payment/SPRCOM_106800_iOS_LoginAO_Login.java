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

import static Listeners_Tests.Listeners_Example.saveTextLog_Allure;

@Listeners(Listeners_Tests.Listeners_Example.class)
@Epic("SPRCOM-92078 My Sprint App iOS - Payment")
@Feature("SPRCOM-106800 My Sprint App iOS - LoginAO Payment")
public class SPRCOM_106800_iOS_LoginAO_Login extends MainBase {

    @Test(groups = {"AccountOwner", "Login"}, priority = 1)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-106802 Test Login with type of account owner")
    public void SPRCOM_106802() throws InterruptedException
    {
        iosDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Make a payment");
        try {
            if(payment_m.size() > 0) {
                TouchAction t = new TouchAction(iosDriver);
                Thread.sleep(500);
                t.tap(PointOption.point(345, 45)).release().perform();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Sign in").click();

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Username").sendKeys("TWREG-51492");
                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementById("Password").sendKeys("T3stM3.P1s");

                iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                iosDriver.findElementByAccessibilityId("Sign In").click();

                System.out.println("INFO: Login Successfully \n -----------------------------------------------");
            } else {
                System.out.println("FAIL: No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }
}
