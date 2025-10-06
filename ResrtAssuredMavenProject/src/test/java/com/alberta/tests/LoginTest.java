package com.alberta.tests;

import base.ApiTestBase;
import org.testng.annotations.Test;

public class LoginTest extends ApiTestBase {

    @Test
    public void verifyLogin() {
        // Call login method directly
        String token = login();


        // Print token just for debug
        System.out.println("Login successful, token: " + token);

        // Simple assertion: token should not be null or empty
        assert token != null && !token.isEmpty() : "Token was not generated!";
    }

}
