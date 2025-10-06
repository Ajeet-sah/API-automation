package Day4;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ParsingjsonResponseData {

    @Test
    public void testExtractToken() {
        RestAssured.baseURI = "https://api.albertapayments.com/v1";

        String requestBody = "{\n" +
                "  \"username\": \"hari101510@gmail.com\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"uuid\": \"123456\"\n" +
                "}";

        // 🔹 First call: Login API
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

        // 🔹 Extract token
        String token = response.jsonPath().getString("data.authorization.token");
        System.out.println("Generated Token: " + token);

        // Extract all stores
        List<Map<String, Object>> stores =
                response.jsonPath().getList("data.user.associated_stores");

        String sid = null;
        for (Map<String, Object> store : stores) {
            if ("Ajeet Testing Store".equals(store.get("store_name"))) {
                sid = String.valueOf(store.get("sid"));
                break;
            }
        }

        System.out.println("Selected Store ID: " + sid);

        // 🔹 Use token + sid in second API
        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/admin/stores/" + sid + "/items/count")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("data.total", greaterThan(0))
                .body("message", equalTo("Items count details fetched successfully"));
    }

}
