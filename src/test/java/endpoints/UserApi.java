package endpoints;

import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserApi {
  public static Response createUser(String name, String job) {
    return given()
        .contentType("application/json")
        .accept("application/json")
        .body(Map.of("name", name, "job", job))
      .when()
        .post("/users");
  }

  public static Response updateUser(String id, String name, String job) {
    return given()
        .contentType("application/json")
        .accept("application/json")
        .body(Map.of("name", name, "job", job))
      .when()
        .put("/users/" + id);
  }

  public static Response getUser(String id) {
    return given()
      .when()
        .get("/users/" + id);
  }

  public static Response deleteUser(String id) {
    return given()
      .when()
        .delete("/users/" + id);
  }
}
