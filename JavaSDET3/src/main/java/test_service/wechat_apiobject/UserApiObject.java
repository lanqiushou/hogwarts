package test_service.wechat_apiobject;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApiObject {
    static final String USER_API_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/";
    private static final Logger logger = LoggerFactory.getLogger(UserApiObject.class);

    /**
     * 依据接口要求的必传参数创建成员
     * @param accessToken
     * @param userid
     * @param name
     * @return
     */
    public static Response createUser(String accessToken, String userid, String name, String mobile) {
        return createUser(accessToken, userid, name, null,
                        mobile, new int[]{1}, new int[]{0}, null,
                        1, null, null, new int[]{0},
                         null, 1, null, true,
                        null, null, null,
                        1);
    }

    /**
     * 使用接口全部字段创建成员，其中accessToken，userid，name为必传字段
      * @param access_token
     * @param userid
     * @param name
     * @param alias
     * @param mobile
     * @param department
     * @param order
     * @param position
     * @param gender
     * @param email
     * @param telephone
     * @param is_leader_in_dept
     * @param avatar_mediaid
     * @param enable
     * @param extattr
     * @param to_invite
     * @param external_profile
     * @param external_position
     * @param address
     * @param main_department
     * @return
     */
    public static Response createUser(String access_token, String userid, String name, String alias,
                                        String mobile, int[] department, int[] order, String position,
                                        int gender, String email, String telephone, int[] is_leader_in_dept,
                                        String avatar_mediaid, int enable, String extattr, boolean to_invite,
                                        String external_profile, String external_position, String address,
                                        int main_department) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/wechat/create_user.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(json);

        if(null != userid){jsonObject.put("userid", userid);}
        if(null != name){jsonObject.put("name", name);}
        if(null != alias){jsonObject.put("alias", alias);}
        if(null != mobile){jsonObject.put("mobile", mobile);}
        if(null != department && department.length != 0){jsonObject.put("department", department);}
        if(null != order && order.length != 0){jsonObject.put("order", order);}
        if(null != position){jsonObject.put("position", position);}
        if(gender == 1 || gender == 2){jsonObject.put("gender", gender);}
        if(null != email){jsonObject.put("email", email);}
        if(null != telephone){jsonObject.put("telephone", telephone);}
        if(null != is_leader_in_dept && is_leader_in_dept.length != 0){jsonObject.put("is_leader_in_dept", is_leader_in_dept);}
        if(null != avatar_mediaid){jsonObject.put("avatar_mediaid", avatar_mediaid);}
        if(enable == 0 || enable == 1){jsonObject.put("enable", enable);}
        if(null != extattr){jsonObject.put("extattr", extattr);}
        if(null != external_profile){jsonObject.put("external_profile", external_profile);}
        if(null != external_position){jsonObject.put("external_position", external_position);}
        if(null != address){jsonObject.put("address", address);}
        if(main_department != 0){jsonObject.put("main_department", main_department);}

        logger.info("request： " + JSONObject.toJSONString(jsonObject));

        Response createResponse = given()
                .when()
                .contentType(ContentType.JSON)
                .body(JSONObject.toJSONString(jsonObject))
                .post(USER_API_URL+"create?access_token="+access_token)
                .then()
//                .log().body()
                .extract()
                .response();

//        logger.info("createResponse: " + createResponse.asString());

        return createResponse;
    }

    /**
     * 获取用户的详细信息
     * @param access_token
     * @param userid
     * @return
     */
    public static Response getUser(String access_token, String userid) {
        return given()
                .when()
                .param("access_token",access_token)
                .param("userid",userid)
                .get(USER_API_URL + "get")
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * 获取部门下面的成员列表
     * @param access_token
     * @param department_id
     * @param fetch_child
     * @return
     */
    public static Response listUser(String access_token, int department_id, int fetch_child) {
        return given()
                .when()
                .param("access_token", access_token)
                .param("department_id", department_id)
                .param("fetch_child", fetch_child)
                .get(USER_API_URL+"simplelist")
                .then()
//                .log().body()
                .extract()
                .response();
    }

    /**
     * 更新用户信息，其中access_token,userid为必输项
     * @param access_token
     * @param userid
     * @param name
     * @param alias
     * @param mobile
     * @param department
     * @param order
     * @param position
     * @param gender
     * @param email
     * @param telephone
     * @param is_leader_in_dept
     * @param avatar_mediaid
     * @param enable
     * @param extattr
     * @param to_invite
     * @param external_profile
     * @param external_position
     * @param address
     * @param main_department
     * @return
     */
    public static Response updateUser(String access_token, String userid, String name, String alias,
                                      String mobile, int[] department, int[] order, String position,
                                      int gender, String email, String telephone, int[] is_leader_in_dept,
                                      String avatar_mediaid, int enable, String extattr, boolean to_invite,
                                      String external_profile, String external_position, String address,
                                      int main_department) {

        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/wechat/update_user.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(json);

        if(null != userid){jsonObject.put("userid", userid);}
        if(null != name){jsonObject.put("name", name);}
        if(null != alias){jsonObject.put("alias", alias);}
        if(null != mobile){jsonObject.put("mobile", mobile);}
        if(null != department && department.length != 0){jsonObject.put("department", department);}
        if(null != order && order.length != 0){jsonObject.put("order", order);}
        if(null != position){jsonObject.put("position", position);}
        if(gender == 1 || gender == 2){jsonObject.put("gender", gender);}
        if(null != email){jsonObject.put("email", email);}
        if(null != telephone){jsonObject.put("telephone", telephone);}
        if(null != is_leader_in_dept && is_leader_in_dept.length != 0){jsonObject.put("is_leader_in_dept", is_leader_in_dept);}
        if(null != avatar_mediaid){jsonObject.put("avatar_mediaid", avatar_mediaid);}
        if(enable == 0 || enable == 1){jsonObject.put("enable", enable);}
        if(null != extattr){jsonObject.put("extattr", extattr);}
        if(null != external_profile){jsonObject.put("external_profile", external_profile);}
        if(null != external_position){jsonObject.put("external_position", external_position);}
        if(null != address){jsonObject.put("address", address);}
        if(main_department != 0){jsonObject.put("main_department", main_department);}

        logger.info("request： " + JSONObject.toJSONString(jsonObject));

        Response updateResponse = given()
                .when()
                .contentType(ContentType.JSON)
                .body(JSONObject.toJSONString(jsonObject))
                .post(USER_API_URL+"update?access_token="+access_token)
                .then()
                .log().body()
                .extract()
                .response();

        return updateResponse;
    }

    /**
     * 根据成员id删除成员
     * @param access_token
     * @param userid
     * @return
     */
    public static Response deleteUser(String access_token, String userid) {
        return given()
                .when()
                .param("access_token",access_token)
                .param("userid",userid)
                .get(USER_API_URL + "delete")
                .then()
                .log().body()
                .extract()
                .response();
    }

    /**
     * 根据userid列表批量删除成员
     * @param access_token
     * @param useridList
     * @return
     */
    public static Response batchDeleteUser(String access_token, List<Object> useridList) {
        String json = null;
        try {
            json = new String(Files.readAllBytes(Paths.get("src/main/resources/wechat/delete_user.json")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = JSONObject.parseObject(json);
        jsonObject.put("useridlist", new JSONArray(useridList));

        Response deleteResponse = given()
                .when()
                .contentType(ContentType.JSON)
                .queryParam("access_token",access_token)
                .body(jsonObject.toJSONString())
                .post(USER_API_URL + "batchdelete")
                .then()
//                .log().body()
                .extract()
                .response();

        return deleteResponse;
    }

    /**
     * 获取部门下的成员
     * @param access_token
     * @param department_id
     * @param fetch_child
     * @return
     */
    public static Response getUser(String access_token, int department_id, int fetch_child) {
        return given()
                .when()
                .param("access_token", access_token)
                .param("department_id", department_id)
                .param("fetch_child", fetch_child)
                .get(USER_API_URL + "list")
                .then()
                .log().body()
                .extract()
                .response();
    }
}
