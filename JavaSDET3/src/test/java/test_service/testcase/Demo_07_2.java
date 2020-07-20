package test_service.testcase;

import com.wechat.apiobject.DepartmentApiObject;
import com.wechat.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * 线程测试
 */
//@Execution(CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Demo_07_2 {
    private static final Logger logger = LoggerFactory.getLogger(Demo_07_2.class);
    static String accessToken;

    @BeforeAll
    public static void getAccessToken() throws Exception {
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @DisplayName("创建部门")
    @RepeatedTest(20)
    @Execution(CONCURRENT)
    void createDepartment() {
        String creatName = "name1234567";
        String creatEnName = "en_name1234567";

        Response creatResponse = DepartmentApiObject.creatDepartMent(creatName, creatEnName, accessToken);
        assertEquals("0", creatResponse.path("errcode").toString());
    }
}
