package test_service.mock;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredTest {
    @Test
    void test() {
        given()
                .get("http://www.baidu.com")
        .then().statusCode(200)
                .log().all();
    }
}
