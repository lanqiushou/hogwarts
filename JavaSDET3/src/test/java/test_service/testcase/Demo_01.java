/**
 * projectName: WeChatWorkApiTest
 * fileName: Demo_01.java
 * packageName: com.wechat.testcase
 * date: 2020-07-18 2:49 下午
 */
package test_service.testcase;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Demo_01
 * @packageName: com.wechat.testcase
 * @description: 部门相关测试j
 * @data: 2020-07-18 2:49 下午
 **/
public class Demo_01 {
    static String accessToken;

    @BeforeAll
    static void getAccessToke() {
        accessToken = given()
                .when()
                .param("corpid", "ww5ef451bf006ec894")
                .param("corpsecret", "EcEIog2OJ8AtO7PDaqt_yqHKqmYXqwSZKDhyfU1aSiU")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract()
                .response()
                .path("access_token");
    }

    @Test
    @Description("创建部门")
    void creatDepartment() {
        String creatBody = "{\n" +
                "   \"name\": \"广州研发中心\",\n" +
                "   \"name_en\": \"RDGZ\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1}";
        Response creatResponse = given()
                .when()
                .contentType("application/json")
                .body(creatBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", creatResponse.path("errcode").toString());
    }

    @Test
    @Description("更新部门")
    void updateDepartment() {
        String updateBody = "{\n" +
                "   \"id\": 3,\n" +
                "   \"name\": \"广州研发中心update\",\n" +
                "   \"name_en\": \"RDGZupdate\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response updateResponse = given()
                .when()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", updateResponse.path("errcode").toString());

    }

    @Test
    @Description("查询部门")
    void listDepartment() {

        Response listResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", "1")
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", listResponse.path("errcode").toString());

    }

    @Test
    @Description("删除部门")
    void deletDepartment() {

        Response deleteResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", "2")
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", deleteResponse.path("errcode").toString());
    }
}