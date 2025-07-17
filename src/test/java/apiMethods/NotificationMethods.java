package apiMethods;

import endpoints.notificationBuilderEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigManager;
import utils.ExtentTestManager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class NotificationMethods {

    // Create Transactional Notification
    public Response createTransactionalNotification(String token) {

        //String token = ConfigManager.getProperty("notificationAuthToken");
        String moduleId = "842e9cd6-7014-4c04-a241-59142ca001b4";
        String name = "Loan Approval QA - " + UUID.randomUUID().toString().substring(0, 8);
        String description = "Generated at " + System.currentTimeMillis();
        Map<String, Object> payload = new HashMap<>();
        payload.put("notificationName", name);
        payload.put("description", description);
        payload.put("moduleId", moduleId);

        ExtentTestManager.getTest().info("Creating notification with payload: " + payload);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(notificationBuilderEndpoints.NOTIFICATION_BASE_URL + notificationBuilderEndpoints.createTransactionMasterAPI)
                .then()
                .extract()
                .response();

        ExtentTestManager.getTest().info("Response: " + response.asPrettyString());
        return response;
    }

    // Get Notification by ID
    public Response getNotificationById(String token, String notificationId) {
        String url = notificationBuilderEndpoints.NOTIFICATION_BASE_URL +
                notificationBuilderEndpoints.getTransactionByIdAPI + "/" + notificationId;

        ExtentTestManager.getTest().info("Fetching notification with ID: " + notificationId);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .when()
                .post(url)
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
