package config;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class RestAssuredConfig {
  @BeforeClass
  public void setup() {
    RestAssured.baseURI  = "https://reqres.in";
    RestAssured.basePath = "/api";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }
}
