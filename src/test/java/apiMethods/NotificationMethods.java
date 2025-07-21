package apiMethods;

import endpoints.notificationBuilderEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ExtentTestManager;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import static io.restassured.RestAssured.given;


public class NotificationMethods {

    public Response createTransactionalNotification(String token) {

        String moduleId = "842e9cd6-7014-4c04-a241-59142ca001b4";
        String name = "Loan Approval QA  " + UUID.randomUUID().toString().substring(0, 8);
        String description = "Generated at " + System.currentTimeMillis();
        Map<String, Object> payload = new HashMap<>();
        payload.put("notificationName", name);
        payload.put("description", description);
        payload.put("moduleId", moduleId);
        /* String rawJson = "{\n" +
                "  \"notificationName\": \"Loan Approval Transaction11231\",\n" +
                "  \"description\": \"\",\n" +
                "  \"moduleId\": \"842e9cd6-7014-4c04-a241-59142ca001b4\"\n" +
                "}";  */
        ExtentTestManager.getTest().info("Creating notification with payload: " + payload);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(notificationBuilderEndpoints.NOTIFICATION_BASE_URL+notificationBuilderEndpoints.createTransactionMasterAPI)
                .then()
                .log().all()
                .extract()
                .response();
        ExtentTestManager.getTest().info("Response: " + response.asPrettyString());
        System.out.println("RESPONSE STATUS: " + response.getStatusCode());
        System.out.println("RESPONSE BODY: " + response.getBody().asString());
        return response;
    }
    // Get Notification by ID
    public Response getNotificationById(String token, String notificationId) {
        String url = notificationBuilderEndpoints.NOTIFICATION_BASE_URL +
                notificationBuilderEndpoints.getTransactionByIdAPI  + notificationId;

        ExtentTestManager.getTest().info("Fetching notification with ID: " + notificationId);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .get(url)
                .then()
                .extract()
                .response();

        ExtentTestManager.getTest().info("Response: " + response.asPrettyString());
        return response;
    }

    // Get All Notifications
    public Response getAllNotifications(String token) {
        String url = notificationBuilderEndpoints.NOTIFICATION_BASE_URL +
                notificationBuilderEndpoints.getAllTransactionDetailsAPI;

        ExtentTestManager.getTest().info("Fetching all notifications");

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(url)
                .then()
                .extract()
                .response();

        ExtentTestManager.getTest().info("Response: " + response.asPrettyString());
        return response;
    }

    // Publish Notification (if applicable)
    public Response publish(String token, String notificationId) {
        String url = notificationBuilderEndpoints.NOTIFICATION_BASE_URL +
                "/notifications/publish/" + notificationId;

        ExtentTestManager.getTest().info("Publishing notification with ID: " + notificationId);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post(url)
                .then()
                .extract()
                .response();

        ExtentTestManager.getTest().info("Response: " + response.asPrettyString());
        return response;
    }

}
