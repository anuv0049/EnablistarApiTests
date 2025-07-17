
package apiMethods;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;



public class LoginMethods {

    public Response loginApi(Map<String,String> payload) {
        return given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("authentication/v1/authentication/login")  // adjust path or prepend base URI if needed
                .then()
                .log().all()
                .extract()
                .response();
    }
}
