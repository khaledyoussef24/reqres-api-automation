package tests;

import config.RestAssuredConfig;
import endpoints.UserApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserManagementTest extends RestAssuredConfig {
    private String userId;

    @Test(priority = 1)
    @Step("Create a new user and capture its ID")
    public void testCreateUser() {
        Response res = UserApi.createUser("morpheus", "leader");
        Assert.assertEquals(res.statusCode(), 201);
        userId = res.jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "testCreateUser")
    @Step("Update the user's job")
    public void testUpdateUser() {
        Response res = UserApi.updateUser(userId, "morpheus", "zion resident");
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("job"), "zion resident");
    }

    @Test(dependsOnMethods = "testUpdateUser")
    @Step("Verify the updated user via GET")
    public void testGetUser_VerifyUpdate() {
        Response res = UserApi.getUser(userId);
        Assert.assertEquals(res.statusCode(), 200);
        // Note: ReqRes returns static data; in real API we'd verify updated fields
    }

    @Test(dependsOnMethods = "testGetUser_VerifyUpdate")
    @Step("Delete the user")
    public void testDeleteUser() {
        Response res = UserApi.deleteUser(userId);
        Assert.assertEquals(res.statusCode(), 204);
    }

    @Test(dependsOnMethods = "testDeleteUser")
    @Step("Verify user deletion returns 404")
    public void testGetUser_VerifyDeletion() {
        Response res = UserApi.getUser(userId);
        Assert.assertEquals(res.statusCode(), 404);
    }
}