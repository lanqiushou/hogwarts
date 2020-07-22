/**
 * projectName: WeChatWorkApiTest
 * fileName: TokenHelper.java
 * packageName: com.wechat.apiobject
 * date: 2020-07-18 5:42 下午
 */
package test_service.wechat_apiobject;

import static io.restassured.RestAssured.given;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: TokenHelper
 * @packageName: com.wechat.apiobject
 * @description: Token相关工具类
 * @data: 2020-07-18 5:42 下午
 **/
public class TokenHelper {
    public static String getAccessToken() {
        String accessToken = given()
                .when()
                .param("corpid", "ww58614e259032068a")
                .param("corpsecret", "ctjFc_mxzJOWaJ19l7gXDLkTOcjBsgbkv-NANicb4dI")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then()
                .log().body()
                .extract()
                .response()
                .path("access_token");
        return accessToken;
    }
}