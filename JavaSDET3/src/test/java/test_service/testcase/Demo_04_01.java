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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_service.task.EvnTask;
import test_service.wechat_apiobject.DepartmentApiObject;
import test_service.wechat_apiobject.TokenHelper;
import test_service.wechat_utils.FakerUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 优化记录：
 * 1、增加了入参实时获取的逻辑
 * 2、增加了脚本的独立性改造
 * 3、通过添加evnClear方法解决脚本无法重复运行的问题
 * 4、对脚本行了分层，减少了重复代码，减少了很多维护成本
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_04_01 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_04_01.class);

    static String accessToken;
    static String departmentId;

    @BeforeAll
    static void getAccessToken() {
        accessToken = TokenHelper.getAccessToken();
    }

    @BeforeEach
    @AfterEach
    void evnClear() {
        EvnTask.clearDepartment(accessToken);
    }

    @Test
    @Description("创建部门")
    @Order(1)
    void creatDepartment() {
        String creatName = "creatName" + FakerUtils.getTimeStamp();
        String creatNameEn = "creatNamEn" + FakerUtils.getTimeStamp();

        Response creatResponse = DepartmentApiObject.creatDepartMent(creatName, creatNameEn, accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;
        assertEquals("0", creatResponse.path("errcode").toString());
    }

    @Test
    @Description("更新部门")
    @Order(2)
    void updateDepartment() {
        Response creatResponse = DepartmentApiObject.creatDepartMent(accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        String updateName = "creatName" + FakerUtils.getTimeStamp();
        String updateNameEn = "creatNamEn" + FakerUtils.getTimeStamp();

        Response updateResponse = DepartmentApiObject.updateDepMent(updateName, updateNameEn, departmentId, accessToken);
        assertEquals("0", updateResponse.path("errcode").toString());

    }

    @Test
    @Description("查询部门")
    @Order(3)
    void listDepartment() {
        Response creatResponse = DepartmentApiObject.creatDepartMent(accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        Response listResponse = DepartmentApiObject.listDepartMent(departmentId, accessToken);
        assertEquals("0", listResponse.path("errcode").toString());

    }

    @Test
    @Description("删除部门")
    @Order(4)
    void deletDepartment() {
        Response creatResponse = DepartmentApiObject.creatDepartMent(accessToken);
        departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        Response deleteResponse = DepartmentApiObject.deleteDepartMent(departmentId, accessToken);
        assertEquals("0", deleteResponse.path("errcode").toString());
    }
}