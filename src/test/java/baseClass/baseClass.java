package baseClass;

import POJO.Login.LoginRequest;
import POJO.Login.LoginResponse;
import config.ConfigManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import utils.Constants;

import static io.restassured.RestAssured.given;

public class baseClass {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.getProperty("devBaseURI");
        performLogin();
    }

    private void performLogin(){
        LoginRequest loginPayload = new LoginRequest(
                ConfigManager.getProperty("username"),
                ConfigManager.getProperty("password")
        );
        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/")
                .then()
                .statusCode(200)
                .extract()
                .response();
        // Deserialize response to POJO
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Extract and store accessToken for later use
        Constants.AUTH_TOKEN = loginResponse.getAccessToken();
    }




}