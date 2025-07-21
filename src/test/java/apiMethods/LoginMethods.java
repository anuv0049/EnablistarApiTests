
package apiMethods;
import endpoints.loginEndpoints;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;



public class LoginMethods {

    public Response loginApi(Map<String,String> payload) {
        return given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post(loginEndpoints.LOGIN_API)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
