package tests;

import config.RestAssuredConfig;
import endpoints.UserApi;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserManagementTest extends RestAssuredConfig {
    private String createdUserId;
    private final String sampleUserId = "2";

    @Test(priority = 1)
    @Step("Create a new user and capture its ID")
    public void testCreateUser() {
        Response res = UserApi.createUser("morpheus", "leader");
        Assert.assertEquals(res.statusCode(), 201);
        createdUserId = res.jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "testCreateUser")
    @Step("Update the sample user's job")
    public void testUpdateUser() {
        Response res = UserApi.updateUser(sampleUserId, "morpheus", "zion resident");
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertEquals(res.jsonPath().getString("job"), "zion resident");
    }

    @Test(dependsOnMethods = "testUpdateUser")
    @Step("Verify the updated sample user via GET")
    public void testGetUser_VerifyUpdate() {
        Response res = UserApi.getUser(sampleUserId);
        Assert.assertEquals(res.statusCode(), 200);
    }

    @Test(dependsOnMethods = "testGetUser_VerifyUpdate")
    @Step("Delete the created user")
    public void testDeleteUser() {
        Response res = UserApi.deleteUser(createdUserId);
        Assert.assertEquals(res.statusCode(), 204);
    }

    @Test(dependsOnMethods = "testDeleteUser")
    @Step("Verify deletion of the created user returns 404")
    public void testGetUser_VerifyDeletion() {
        Response res = UserApi.getUser(createdUserId);
        Assert.assertEquals(res.statusCode(), 404);
    }
}
