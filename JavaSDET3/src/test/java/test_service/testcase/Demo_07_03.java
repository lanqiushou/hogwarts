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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_service.wechat_apiobject.DepartmentApiObject;
import test_service.wechat_apiobject.TokenHelper;
import test_service.wechat_utils.FakerUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * 优化记录：
 * 1、增加了入参实时获取的逻辑
 * 2、增加了脚本的独立性改造
 * 3、通过添加evnClear方法解决脚本无法重复运行的问题
 * 4、对脚本行了分层，减少了重复代码，减少了很多维护成本
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_07_03 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_07_03.class);

    static String accessToken;
    static String departmentId;

    @BeforeAll
    static void getAccessToken() {
        accessToken = TokenHelper.getAccessToken();
    }


    @Description("创建部门")
    @RepeatedTest(10)
    @Execution(CONCURRENT)
    void creatDepartment() {
        String backentStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();
        String creatName = "creatName" + backentStr;
        String creatNameEn = "creatNamEn" + backentStr;

        Response creatResponse = DepartmentApiObject.creatDepartMent(creatName, creatNameEn, accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;
        assertEquals("0", creatResponse.path("errcode").toString());
    }

    @Description("更新部门")
    @RepeatedTest(10)
    @Execution(CONCURRENT)
    void updateDepartment() {
        String backentStr = Thread.currentThread().getId() + FakerUtils.getTimeStamp();

        String creatName = "creatName" + backentStr;
        String creatNameEn = "creatNamEn" + backentStr;

        Response creatResponse = DepartmentApiObject.creatDepartMent(creatName, creatNameEn, accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        String updateName = "updateName" + backentStr;
        String updateNameEn = "updateNameEn" + backentStr;

        Response updateResponse = DepartmentApiObject.updateDepMent(updateName, updateNameEn, departmentId, accessToken);
        assertEquals("0", updateResponse.path("errcode").toString());

    }

}