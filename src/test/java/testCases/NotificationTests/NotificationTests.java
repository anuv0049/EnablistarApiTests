package testCases.NotificationTests;

import apiMethods.NotificationMethods;
import baseClass.BaseClass;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigManager;
import utils.ExtentTestManager;


public class NotificationTests extends BaseClass {


    NotificationMethods notificationMethods = new NotificationMethods();
    String recentNotificationID = "";
    String token = ConfigManager.getProperty("notificationAuthToken");
    String moduleId = "842e9cd6-7014-4c04-a241-59142ca001b4";

    @Test(priority = 1, testName = "TC01: Create Transactional Notification")
    public void createTransactionalNotificationTest() {

        Response response = notificationMethods.createTransactionalNotification(token);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on creation");
        recentNotificationID = response.jsonPath().getString("id"); // adjust key if needed
        ExtentTestManager.getTest().info("Created notification ID: " + recentNotificationID);
        Assert.assertNotNull(recentNotificationID, "Notification ID should not be null");




    }


    @Test(priority = 2,dependsOnMethods = "createTransactionalNotificationTest", testName = "TC02: Fetch Notification By ID")
    public void fetchNotificationByIdTest() {
        Response response = notificationMethods.getNotificationById(token, recentNotificationID);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on fetch by ID");
        ExtentTestManager.getTest().info("Fetched notification by ID: " + recentNotificationID);
    }

    @Test(priority = 3,dependsOnMethods = "createTransactionalNotificationTest", testName = "TC03: Publish Notification")
    public void publishNotificationTest() {
        Response response = notificationMethods.publish(token, recentNotificationID);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on publish");
        ExtentTestManager.getTest().info("Published notification ID: " + recentNotificationID);
    }

    @Test(priority = 4, testName = "TC04: Get All Notifications")
    public void getAllNotificationsTest() {
        Response response = notificationMethods.getAllNotifications(token);

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200 on getAllNotificationAPI");
        ExtentTestManager.getTest().info("Fetched all notifications");
    }

}
