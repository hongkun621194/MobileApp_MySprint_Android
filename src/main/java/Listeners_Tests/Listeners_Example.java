package Listeners_Tests;

import iOS_Tests.MainBase;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listeners_Example extends MainBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Auto Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG_Allure(IOSDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Fail Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG_Allure_Fail(IOSDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "IMPORTANT Message", type = "text/plain")
    public static String saveTextLog_Allure(String message) {
        return message;
    }

    @Attachment(value = "Expected Result", type = "text/plain")
    public static String saveTextLog_Allure_er(String message) {
        return message;
    }

    @Attachment(value = "Video Attachment", type = "video/mp4")
    public static byte[] saveH264_Allure_Video(String videoClip) {
        return Base64.decodeBase64(videoClip);
    }

    @Attachment(value = "Video Attachment File", type = "video/mp4", fileExtension = ".mp4")
    public static byte[] attach_FilePathVideo(String pathName) {
        try {
//            File mp4 = new File(System.getProperty("java.io.tmpdir") + "temp.mp4");
//            mp4.deleteOnExit();
////            URL videoUrl = new URL(selenoidUrl + "/video/" + sessionId + ".mp4");
//            File new_file = new File(pathName+".mp4");
//            FileUtils.copyFile(local_file, new_file);
//            FileUtils.copyURLToFile(new URL(pathName), mp4);
            File file_name = new File(pathName);
            File local_file = new File(file_name.getAbsolutePath());
            return FileUtils.readFileToByteArray(local_file);
//            return Files.toByteArray(local_file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("LISTENER: onStart Test - " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("LISTENER: onFinish Test - " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        iosDriver.startRecordingScreen(IOSStartScreenRecordingOptions.startScreenRecordingOptions().withVideoType("h264"));
        System.out.println("LISTENER: The Test -" + getTestMethodName(iTestResult) + "- is Starting");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveH264_Allure_Video(iosDriver.stopRecordingScreen());
        System.out.println("LISTENER: The Test -" + getTestMethodName(iTestResult) + "- passed");
    }

    // This belongs to ITestListener and will execute when a test a failed
    @Override
    public void onTestFailure(ITestResult iTestResult)
    {
        ITestContext context = iTestResult.getTestContext();
        saveScreenshotPNG_Allure(iosDriver);
        saveH264_Allure_Video(iosDriver.stopRecordingScreen());
        System.out.println("LISTENER: Test failed ... " + iTestResult.getName() + " --- " + context) ;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        saveH264_Allure_Video(iosDriver.stopRecordingScreen());
        System.out.println("LISTENER: The Test -" + getTestMethodName(iTestResult) + "- is Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("LISTENER: Test failed but it is in defined success ratio - " + getTestMethodName(iTestResult));
    }

}
