package endpoints;

import io.restassured.response.Response;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String API_KEY_HEADER = "x-api-key";
    private static final String API_KEY_VALUE  = "reqres-free-v1";

    public static Response createUser(String name, String job) {
        return given()
                .header(API_KEY_HEADER, API_KEY_VALUE)         // ← REQUIRED API KEY HEADER
                .contentType("application/json")
                .accept("application/json")
                .log().all()
                .body(Map.of("name", name, "job", job))
            .when()
                .post("/users");   // https://reqres.in/api/users
    }

    public static Response updateUser(String id, String name, String job) {
        return given()
                .header(API_KEY_HEADER, API_KEY_VALUE)  
                .contentType("application/json")
                .accept("application/json")
                .log().all()
                .body(Map.of("name", name, "job", job))
            .when()
                .put("/users/" + id);
    }

    public static Response getUser(String id) {
        return given()
                .header(API_KEY_HEADER, API_KEY_VALUE)
                .log().all()
            .when()
                .get("/users/" + id);
    }

    public static Response deleteUser(String id) {
        return given()
                .header(API_KEY_HEADER, API_KEY_VALUE)
                .log().all()
            .when()
                .delete("/users/" + id);
    }
}
