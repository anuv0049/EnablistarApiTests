
package apiMethods;
import io.restassured.response.Response;
import pojoFiles.loginPojo;
import static io.restassured.RestAssured.given;



public class LoginMethods {

    public Response loginApi(loginPojo payload) {
        return given()
                .contentType("application/json")
                .body(payload)
                .when()
                .post("/auth/login")  // adjust path or prepend base URI if needed
                .then()
                .extract()
                .response();
    }
}
