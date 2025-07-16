package apiMethods;


import endpoints.notificationBuilderEndpoints;
import io.restassured.response.Response;
import utils.ConfigManager;

import static io.restassured.RestAssured.given;

public class NotificationMethods {

    public Response getNotifications(String token) {
        return given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(notificationBuilderEndpoints.NOTIFICATION_BASE_URL+ notificationBuilderEndpoints.getAllTransactionDetailsAPI)
                .then()
                .extract()
                .response();
    }
}
