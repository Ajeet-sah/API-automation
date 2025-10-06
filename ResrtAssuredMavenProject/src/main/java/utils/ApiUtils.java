package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    // Set base URI (change it as per your project)
    static {
        RestAssured.baseURI = "https://api.example.com";
    }

    // ---------- BASIC REQUEST METHODS ----------

    /**
     * GET request with headers & query params
     */

        public static void getRequest(String endpoint, Map<String, String> headers, Map<String, String> queryParams) {
        Response response = given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();

        // You can log response details here if you still want to see them
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.asString());
    }

   /* public static  Response getRequest(String endpoint, Map<String, String> headers, Map<String, String> queryParams) {
        return given()
                .headers(headers)
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .log().all()
                .extract().response();
    }*/

    /**
     * POST request with body & headers
     */
    public static Response postRequest(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * PUT request
     */
    public static Response putRequest(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .body(body)
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * DELETE request
     */
    public static Response deleteRequest(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * PATCH request
     */
    public static Response patchRequest(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .body(body)
                .when()
                .patch(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * HEAD request
     */
    public static Response headRequest(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .head(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * OPTIONS request
     */
    public static Response optionsRequest(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .options(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    // ---------- FILE HANDLING ----------

    /**
     * File Upload (multipart/form-data)
     */
    public static Response uploadFile(String endpoint, Map<String, String> headers, File file, String paramName) {
        return given()
                .headers(headers)
                .multiPart(paramName, file)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
    }

    /**
     * Download file as byte[]
     */
    public static byte[] downloadFile(String endpoint, Map<String, String> headers) {
        return given()
                .headers(headers)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .asByteArray();
    }

    // ---------- ASSERTIONS / HELPERS ----------

    /**
     * Validate Status Code
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        if (response.getStatusCode() != expectedStatusCode) {
            throw new AssertionError("❌ Expected " + expectedStatusCode + " but got " + response.getStatusCode());
        }
    }

    /**
     * Extract value from JSON response
     */
    public static String getJsonValue(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

}
