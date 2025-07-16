
package testCases.NotificationTests;

import apiMethods.NotificationMethods;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import utils.ConfigManager;

public class notificationTests {

    NotificationMethods notificationMethods = new NotificationMethods();

    @Test(testName = "TC01_Get_All_Notifications")
    public void notificationTest1() {
        Response response = notificationMethods.getNotifications(ConfigManager.getProperty("notificationAuthToken"));
//Response response = notificationMethods.getNotifications(ConfigManager.getProperty("notificationAuthToken")); for qa
        System.out.println("Response:\n" + response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

}
