package baseClass;

import apiMethods.LoginMethods;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pojoFiles.loginPojo;
import utils.ConfigManager;
import utils.Constants;
import io.restassured.RestAssured;


public class BaseClass {

    protected static String accessToken;

    @BeforeClass
    public void setup() {

        // Set the base URI from properties file
        String baseURI = ConfigManager.getProperty("devBaseURI");
        RestAssured.baseURI = baseURI;

        // Prepare login payload using constants
        loginPojo loginPayload = new loginPojo(Constants.LOGIN_USERNAME, Constants.LOGIN_PASSWORD);

        // Perform login using LoginMethods
        LoginMethods loginMethods = new LoginMethods();
        Response response = loginMethods.loginApi(loginPayload);

        // Extract access token if login is successful
        if (response.getStatusCode() == 200) {
            accessToken = response.jsonPath().getString("token");
            System.out.println("Login successful. Access token: " + accessToken);

          } else {

            throw new RuntimeException("Login failed. Status Code: " + response.getStatusCode());
        }
    }

    @BeforeMethod
    public void beforeMethod(org.testng.ITestResult result) {
        // Create test for each test method (you can override this in child classes or use listeners)

    }


}
