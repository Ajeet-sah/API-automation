package com.alberta.tests;
import base.ApiTestBase;
import io.restassured.response.Response;
import utils.PropertiesOperations;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ItemsCountTest extends ApiTestBase {

    private static String sid = PropertiesOperations.getPropertyValueByKey("sid");

    Response response =
            (Response) given()
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .get("/admin/stores/" + sid + "/items/count")
                    .then()
                    .statusCode(200)
                    .body("success", equalTo(true))
                    .body("data.total", greaterThan(0))
                    .body("message", equalTo("Items count details fetched successfully"));
}
