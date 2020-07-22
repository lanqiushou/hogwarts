/**
 * projectName: WeChatWorkApiTest
 * fileName: Demo_01.java
 * packageName: com.wechat.testcase
 * date: 2020-07-18 2:49 下午
 */
package test_service.testcase;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import test_service.wechat_utils.FakerUtils;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化记录：
 * 1、增加了入参实时获取的逻辑
 * 2、增加了脚本的独立性改造
 * 3、使用时间戳命名法避免数据重复的问题
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_03 {
    static String accessToken;
    static String departmentId;

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
    @Order(1)
    void creatDepartment() {
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();
        String creatBody = "{\n" +
                "   \"name\": \"" + creatName + "\",\n" +
                "   \"name_en\": \"" + creatNameEn + "\",\n" +
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
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;
        assertEquals("0", creatResponse.path("errcode").toString());
    }

    @Test
    @Description("更新部门")
    void updateDepartment() {
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();
        String creatBody = "{\n" +
                "   \"name\": \"" + creatName + "\",\n" +
                "   \"name_en\": \"" + creatNameEn + "\",\n" +
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
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        String updateName = "creatName" + FakerUtils.getTimeStamp();
        String updateNameEn = "creatNamEn" + FakerUtils.getTimeStamp();

        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"" + updateName + "\",\n" +
                "   \"name_en\": \"" + updateNameEn + "\",\n" +
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
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();
        String creatBody = "{\n" +
                "   \"name\": \"" + creatName + "\",\n" +
                "   \"name_en\": \"" + creatNameEn + "\",\n" +
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
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;
        Response listResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", departmentId)
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
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();
        String creatBody = "{\n" +
                "   \"name\": \"" + creatName + "\",\n" +
                "   \"name_en\": \"" + creatNameEn + "\",\n" +
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
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;
        Response deleteResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract()
                .response();
        assertEquals("0", deleteResponse.path("errcode").toString());
    }
}