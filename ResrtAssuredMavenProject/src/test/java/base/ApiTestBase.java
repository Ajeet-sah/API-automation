package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import utils.ApiUtils;
import utils.PropertiesOperations;

public class ApiTestBase extends ApiUtils {

    public static String token;

    @BeforeClass
    public void setup() {
        // Set Base URI from config
        RestAssured.baseURI = PropertiesOperations.getPropertyValueByKey("baseURL");
        System.out.println("Base URI set to: " + RestAssured.baseURI);

        // Generate token before any test
        //token = login();
    }

    public static String login() {
        // Create request body using values from config.properties
        String requestBody = "{\n" +
                "  \"username\": \"" + PropertiesOperations.getPropertyValueByKey("username") + "\",\n" +
                "  \"password\": \"" + PropertiesOperations.getPropertyValueByKey("password") + "\",\n" +
                "  \"uuid\": \"" + PropertiesOperations.getPropertyValueByKey("uuid") + "\"\n" +
                "}";

        // Call login API
        Response response =
                given()
                        .contentType("application/json")
                        .body(requestBody)
                        .when()
                        .post("/admin/login")
                        .then()
                        .statusCode(200)
                        .body("message", equalTo("Token generated successfully"))
                        .extract().response();

        // Extract token
        String token = response.jsonPath().getString("data.authorization.token");
        System.out.println("Generated Token: " + token);

        return token;
    }
}
