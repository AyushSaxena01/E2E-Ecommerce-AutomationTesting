package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class listener extends utilities implements ITestListener {
    String name;
    ExtentTest test;
    ExtentReports report = utilities.getReportObject();
    ThreadLocal<ExtentTest> extenttest = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        try{
            name=utilities.setName(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        test = report.createTest(result.getMethod().getMethodName()).assignCategory(name);
        extenttest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extenttest.get().log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = null;
        extenttest.get().fail(result.getThrowable());
        String testMethod = result.getMethod().getMethodName();
        try{
            driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }


}
