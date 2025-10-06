package in.act;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class RestAsuredDemo {
    public static void main(String[] args) {
        //way 1
        RestAssured.baseURI = "https://api.albertapayments.com/v1/admin/login";
        RequestSpecification req = given();
        Response res = req.get();
        int statusCode = res.getStatusCode();
        Assert.assertEquals(statusCode, 200);

        //way 2
        given()
                .baseUri("https://api.albertapayments.com/v1/admin/login")
                .when()
                .get()
                .then()
                .statusCode(200);
    }

}
