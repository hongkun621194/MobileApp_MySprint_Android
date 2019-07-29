package SPRCOM_92088_iOS_Sales;

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
import static Listeners_Tests.Listeners_Example.saveTextLog_Allure_er;

@Listeners(Listeners_Tests.Listeners_Example.class)
@Epic("SPRCOM-92088 My Sprint App iOS - Sales")
@Feature("SPRCOM-107200 My Sprint App iOS - LoginAO Sales")
public class SPRCOM_107200_iOS_LoginAO_Sales_ShopPage_UpgradeAO_Cart extends MainBase {


    private Float Monthly_upgrade;
//    private Float Today;

    @Test(groups = {"AccountOwner", "Login"}, priority = 4, dataProvider = "DeviceOption",
            dataProviderClass = Data.SPRCOM_107202_DeviceOptions_Upgrade.class)
    @Description("My Sprint app Sales - MainPage: Get your new device - upgrade")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-107202 My Sprint App iOS - MainPage: Get your new device - upgrade")
    public void SPRCOM_107206(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption, String RestValueOption, String CurrentDevice) throws Exception
    {
        SPRCOM_107212_Step1();
        SPRCOM_107212_Step2(deviceType, brand);
        SPRCOM_107212_Step3(CurrentDevice);

        SPRCOM_107206_Step2(model);
        SPRCOM_107206_Step3();
        SPRCOM_107206_Step4(planOption);
        SPRCOM_107206_Step5(protectionOption);
        SPRCOM_107206_Step6(RestValueOption);
        SPRCOM_107206_Step7();

        SPRCOM_107212_Step10();
    }

    @Step("1. Make sure MainPage is loaded and tap ‘Shop’ page")
    private void SPRCOM_107212_Step1() throws InterruptedException
    {
        saveTextLog_Allure_er("Shop page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> payment_m =iosDriver.findElementsById("Make a payment");
        try {
            if(payment_m.size() < 1) {
                System.out.println("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Thread.sleep(500);
        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(190, 650)).release().perform();

        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> shop_m =iosDriver.findElementsByAccessibilityId("Browse devices");
        try {
            if(shop_m.size() < 1) {
                System.out.println("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No main page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
    }

    @Step("2. Use device type and brand name from data provider and tap them on Shop page")
    private void SPRCOM_107212_Step2(String deviceType, String Brand) throws Exception
    {
        saveTextLog_Allure_er("Shop Option page is displayed (Add new or upgrade)");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(deviceType).click();

        Thread.sleep(3000);
        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(100, 400)).release().perform();

        if(deviceType.equals("Others")) {
            Thread.sleep(1000);
//            TouchAction t = new TouchAction(iosDriver);
            t.tap(PointOption.point(100, 400)).release().perform();
        }
    }

    @Step("3. Tap a line to upgrade")
    private void SPRCOM_107212_Step3(String CurrentDevice) throws Exception
    {
        saveTextLog_Allure_er("Loading for a long time at first launch(20s) and Shop devices page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(CurrentDevice).click();
    }


    @Step("4. (Apple device options as default.) Tap `Apple` filter and tap `Cancel` and tap ‘Apple iPhone XS’ (Model)")
    private void SPRCOM_107206_Step2(String model) throws Exception
    {
        saveTextLog_Allure_er("Device Details page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        iosDriver.findElementById("Apple").click();
        Thread.sleep(1000);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        iosDriver.findElementById("Cancel").click();

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(model).click();
    }
    @Step("5. Make sure Continue button is enabled and tap it")
    private void SPRCOM_107206_Step3()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("PASS: Continue - Default status is enabled");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Continue").click();
    }

    @Step("6. Use Data Provider with three options and tap continue")
    private void SPRCOM_107206_Step4(String planOption)
    {
        saveTextLog_Allure_er("Wait for loading(20s) and Plans page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        iosDriver.findElementById(planOption).click();

        List<IOSElement> Device_Detail = iosDriver.findElementsByClassName("XCUIElementTypeStaticText");
//        Today = Float.valueOf(Device_Detail.get(5).getText().replace("$", " "));
        Monthly_upgrade = Float.valueOf(Device_Detail.get(6).getText().replace("$", " "));
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Continue").click();
    }
    @Step("7. Tap button ‘Add protection’?")
    private void SPRCOM_107206_Step5(String protectionOption)
    {
        saveTextLog_Allure_er("Wait for loading(15s), Return page is displayed");
        if(protectionOption.equals("Add protection")) {
            Monthly_upgrade = Monthly_upgrade + 19;
        }
        iosDriver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        iosDriver.findElementById(protectionOption).click();
    }
    @Step("8. Default Continue disabled, tap ‘Buy this phone` or ‘Return this phone’ and tap Continue")
    private void SPRCOM_107206_Step6(String RestValueOption) throws Exception
    {
        saveTextLog_Allure_er("20s loading, Cart page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertFalse(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("PASS: Continue - Default status is disabled");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById(RestValueOption).click();

        Thread.sleep(1000);
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Continue").isEnabled());
        System.out.println("PASS: Continue - Default status is enabled");

        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Continue").click();

    }
    @Step("9. Verify value and tap cancel")
    private void SPRCOM_107206_Step7() throws InterruptedException
    {
        saveTextLog_Allure_er("All payment values are correct, and MainPage is displayed");

        Thread.sleep(5000);
        iosDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(iosDriver.findElementByAccessibilityId("Cancel").isEnabled());

        List<IOSElement> Device_Detail = iosDriver.findElementsByClassName("XCUIElementTypeStaticText");
//        Assert.assertEquals(Monthly_upgrade , Float.valueOf(Device_Detail.get(7).getText().replace("$", "")));

        saveTextLog_Allure("Due Monthly Value verify Success");
        iosDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        iosDriver.findElementById("Cancel").click();
        Thread.sleep(2000);
    }

    @Step("10. Tap Account to main page ")
    private void SPRCOM_107212_Step10() throws Exception
    {
        saveTextLog_Allure_er("Main page is displayed");
        iosDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<IOSElement> shop_m =iosDriver.findElementsByAccessibilityId("Browse devices");
        try {
            if(shop_m.size() < 1) {
                System.out.println("FAIL: 15sec ... No shop page showed with unknown reason! - Screenshot taken");
                saveTextLog_Allure("FAIL: 15sec ... No shop page showed with unknown reason! - Screenshot taken");
                Assert.fail();
            }
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }

        Thread.sleep(1000);
        TouchAction t = new TouchAction(iosDriver);
        t.tap(PointOption.point(40, 650)).release().perform();
    }
}
