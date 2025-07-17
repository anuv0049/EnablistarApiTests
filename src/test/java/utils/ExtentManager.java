package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("API Test Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Your Name");
        }
        return extent;
    }
}
