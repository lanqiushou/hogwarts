package test_service.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import test_service.utils.Filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class InterfaceTest {

    private static String access_token;

    @Test
    void practice() {
//        given().get("http://localhost:8000/products-schema.json")
//                .then().assertThat().body(matchesJsonSchemaInClasspath("products-schema.json"));
//
//        ObjectMapper

//        MyInterface myInterface = (a, b) -> {
//            return a+b;
//        };


        String access_token = "7XgG0RIDwRB8kM51bXtt44pUcCcCUnwodeXCdDKR-NpWBuQqjEqgXTZ5UtMqS8kzgQCkGQr98_djFqkirlEGruA4SBY7vRTKZqy7lpv4hvjgK7kXjRZ48EPdNv0Mmoti1YFzPySpABs5LLFfERDfIzuHfTBDOp8GZCoREndjR1CbjpqfNgymxYx10hqJcRDUWdgbgP999Bl3069S_gRMbQ";

        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/wechat/add_tag.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        given().when().queryParam("access_token", access_token)
                .contentType(ContentType.JSON)
                .body(json)
                .log().all()
                .post("https://qyapi.weixin.qq.com/cgi-bin/externalcontact/add_corp_tag")
                .then()
                .log().all();

    }

    interface MyInterface {
        public int add(int a, int b);
//        public int length(String a, String b);
    }

    @Test
    static void interfaceTest() {
        String corpId = "ww58614e259032068a";
        String corpsecret = "ctjFc_mxzJOWaJ19l7gXDMkvt6YQE2XBrB-B3hkR5u8";

        access_token = given()
                .when()
                .param("corpid", corpId)
                .param("corpsecret", corpsecret)
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract().body()
                .path("access_token");

        System.out.println(access_token);
    }

    @Test
    void ceshiren_encode2() {
        given()
                .filter(Filters.base64Decode())
                .filter(Filters.base64Decode())
                .get("http://shell.ceshiren.com:8000/encode2.json")
                .then()
                .body("category_list.categories[0].name", equalTo("霍格沃兹测试学院公众号"));

    }

}
