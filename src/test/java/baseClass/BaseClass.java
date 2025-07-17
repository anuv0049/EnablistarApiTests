package baseClass;

import apiMethods.LoginMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.annotations.*;

import utils.Constants;
import utils.ExtentManager;
import utils.ExtentTestManager;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseClass {

    protected static String accessToken;

    @BeforeSuite
    public void beforeSuite() {
        // Initialize report instance
        ExtentManager.getInstance();
    }

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://devapi.enablistar.com/";
        // Or use: RestAssured.baseURI = ConfigManager.getProperty("baseURI");

        Map<String, String> loginPayload = new HashMap<>();
        loginPayload.put("Username", Constants.LOGIN_USERNAME);
        loginPayload.put("Password", Constants.LOGIN_PASSWORD);

        LoginMethods loginMethods = new LoginMethods();
        Response response = loginMethods.loginApi(loginPayload);

        if (response.getStatusCode() == 200) {
            accessToken = response.jsonPath().getString("accessToken");
            System.out.println("Login successful. Access token: " + accessToken);
        } else {
            throw new RuntimeException("Login failed. Status Code: " + response.getStatusCode());
        }
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        String testName = method.getAnnotation(Test.class) != null && !method.getAnnotation(Test.class).testName().isEmpty()
                ? method.getAnnotation(Test.class).testName()
                : method.getName();

        ExtentTestManager.startTest(testName, "Executing test: " + testName);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().skip("Test skipped");
        }

        ExtentTestManager.endTest(); // Remove thread-local instance
    }

    @AfterSuite
    public void afterSuite() {
        ExtentManager.getInstance().flush(); // Final report write
    }
}
