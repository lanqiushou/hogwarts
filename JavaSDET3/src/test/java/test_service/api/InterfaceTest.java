package test_service.api;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class InterfaceTest {

    private static String access_token;

    @BeforeAll
    static void interfaceTest() {
        String corpId = "ww58614e259032068a";
        String corpsecret = "ctjFc_mxzJOWaJ19l7gXDMkvt6YQE2XBrB-B3hkR5u8";

        access_token = given()
                .when()
                .param("corpid",corpId )
                .param("corpsecret",corpsecret)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract().body()
                .path("access_token");

        System.out.println(access_token);
    }

}
