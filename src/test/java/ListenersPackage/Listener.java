package ListenersPackage;

import Base.BaseClass;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        BaseClass.test = BaseClass.extent.createTest(result.getMethod().getMethodName());
        BaseClass.test.log(Status.INFO, "Starting " + result.getMethod().getMethodName() + " Test");
        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseClass.test.log(Status.PASS, result.getMethod().getMethodName() + " passed");
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseClass.test.log(Status.FAIL, result.getMethod().getMethodName() + " failed");
        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        BaseClass.test.log(Status.INFO, "Finished Main Test");
        BaseClass.extent.flush();
        ITestListener.super.onFinish(context);
    }
}
