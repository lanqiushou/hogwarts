/**
 * projectName: WeChatWorkApiTest
 * fileName: DepartmentApiObject.java
 * packageName: com.wechat.apiobject
 * date: 2020-07-18 5:08 下午
 */
package test_service.wechat_apiobject;

import io.restassured.response.Response;
import test_service.utils.FakerUtils;

import static io.restassured.RestAssured.given;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: DepartmentApiObject
 * @packageName: com.wechat.apiobject
 * @description: 部门相关的方法对象
 * @data: 2020-07-18 5:08 下午
 **/
public class DepartmentApiObject {
    public static Response creatDepartMent(String creatName, String creatNameEn, String accessToken) {
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
        return creatResponse;
    }

    public static Response creatDepartMent(String accessToken) {
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();
        return creatDepartMent(creatName, creatNameEn, accessToken);
    }

    public static Response updateDepMent(String updateName, String updateNameEn, String departmentId, String accessToken) {
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
        return updateResponse;
    }

    public static Response listDepartMent(String departmentId, String accessToken) {
        Response listResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then()
                .log().body()
                .extract()
                .response();
        return listResponse;
    }

    public static Response deleteDepartMent(String departmentId, String accessToken) {
        Response deleteResponse = given()
                .when()
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract()
                .response();
        return deleteResponse;
    }
}