package test_framework_service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

/**
 * 代表了一个单一的http接口
 */
public class ApiObjectMethodModel {
    public HashMap<String, Object> query;
    public HashMap<String, Object> save;
    public HashMap<String, Object> json;
    public String post;
    public String get;

    /**
     * 发送http请求
     * @return
     * @param params
     */
    public Response run(HashMap<String, Object> params) {
        //多环境支持
        String method = null;
        String url="";


        if(post!=null){
            url=post;
            method="post";
        }
        if(get!=null){
            url=get;
            method="get";
        }
        //读取配置文件，获得域名与ip对应关系，在此替换
//        url=url.replaceAll("domain", "ip");

        if(json == null) {json = new HashMap<>();}

        if(query == null) {query = new HashMap<>();}

        if(params == null) {
            params = new HashMap<String, Object>();
        }

        //加入测试中立执行过程中产生的数据
        params.putAll(ApiTestCaseModel.getParams());

        HashMap<String, Object> finalParams = params;
        System.out.println(finalParams);

        params.forEach((key, value) -> {
            String matcher = "${" + key + "}";

            if(query.containsValue(matcher)) {
                query.put(key, finalParams.get(key));
            }

            if(json.containsValue(matcher)) {
                json.put(key, finalParams.get(key));
            }
        });

        Response response = given().log().all()
                .queryParams(query).contentType(ContentType.JSON).body(json).request(method, url)
                .then().log().all().extract().response();

        if(save != null && save.size() != 0) {
            save.forEach((path, var) -> {
                ApiTestCaseModel.addParams((String) var, response.path(path)); //保存测试用例执行过程中产生的数据
            });
        }

        return response;
    }
}
