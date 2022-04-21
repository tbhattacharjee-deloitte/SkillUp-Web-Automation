package ListenersPackage;

import TestClasses.AdminManagerTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AdminManagerListener extends AdminManagerTest implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest("Create User");
        test.log(Status.INFO, "Starting " + result.getMethod().getMethodName() + " Test");
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getMethod().getMethodName() + " passed");
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.PASS, result.getMethod().getMethodName() + " failed");
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        test.log(Status.INFO, "Finished Main Test");
        extent.flush();
        ITestListener.super.onFinish(context);
    }
}
