package test_service.testcase;

import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test_service.task.EvnTask;
import test_service.wechat_apiobject.TokenHelper;
import test_service.wechat_apiobject.UserApiObject;
import test_service.wechat_utils.FakerUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class UserApiTest {
    private static final Logger logger = LoggerFactory.getLogger(UserApiTest.class);

    static String accessToken;
    static int departmentId = 1;

    @BeforeAll
    static void getAccessToken() {
        accessToken = TokenHelper.getAccessToken();
//        accessToken = "LasXky-OjcIP3aSVLWrGK71cFgDYligMqw9VwSmWEZXoVNPqnN8WwTyj7pDxVSk2xHCskWV56gWFwMaSI0jVhAwI5TCRW1ZI7RfpoUpnsFyuSpXnymeA6QVjU51YAAcCDw4ibxQrx2Vf2GjoIGYMcdF8U4RR5x1Ue0fbg6QycK7VQO50hWEo9x-wKO1eVCw4nzuJByjo97WR-APRXqCReg";
    }

    @BeforeEach
    @AfterEach
    void clearUser() {
        EvnTask.clearUser(accessToken,departmentId,1);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/wechat/data/createUser.csv", numLinesToSkip = 1)
    void createUser(String userid, String name, String alias,
                    String mobile, String department, String order, String position,
                    String gender, String email, String telephone, String is_leader_in_dept,
                    String avatar_mediaid, String enable, String extattr, String to_invite,
                    String external_profile, String external_position, String address,
                    String main_department, String returnCode) {

        Response createResponse = UserApiObject.createUser(accessToken, userid, name, alias,
                mobile, FakerUtils.string2IntArray(department), FakerUtils.string2IntArray(order), position,
                FakerUtils.string2int(gender), email, telephone, FakerUtils.string2IntArray(is_leader_in_dept),
                avatar_mediaid, FakerUtils.string2int(enable), extattr, FakerUtils.string2bool(to_invite),
                external_profile, external_position, address,
                FakerUtils.string2int(main_department));

        assertEquals(returnCode, createResponse.path("errcode").toString());
    }

//    @Test
//    void prepareData() {
//        String test = "{1,2,3}";
//
//        int[] testInt = Stream.of(test.replaceAll("[\\{\\}]", "").split(",")).mapToInt(Integer::parseInt).toArray();
//
//        for(int i : testInt) {
//            System.out.println(i);
//        }
//    }

    @Test
    void listUser() {
        String createUserId1 = "tengjb" + FakerUtils.getTimeStamp();
        String createUserName1 = "tengjingbao" + FakerUtils.getTimeStamp();
        String mobile1 = ChineseMobileNumberGenerator.getInstance().generate();
        UserApiObject.createUser(accessToken, createUserId1, createUserName1, mobile1);

        String createUserId2 = "tengjb" + FakerUtils.getTimeStamp();
        String createUserName2 = "tengjingbao" + FakerUtils.getTimeStamp();
        String mobile2 = ChineseMobileNumberGenerator.getInstance().generate();
        UserApiObject.createUser(accessToken, createUserId2, createUserName2, mobile2);

        Response listResponse = UserApiObject.listUser(accessToken, departmentId, 0);

        assertAll("返回值校验：",
                () -> assertEquals("0", listResponse.path("errcode").toString()),
                () -> assertTrue(((ArrayList<String>)listResponse.path("userlist.userid")).containsAll(Arrays.asList(createUserId1, createUserId2))),
                () -> assertTrue(((ArrayList<String>)listResponse.path("userlist.name")).containsAll(Arrays.asList(createUserName1, createUserName2)))
        );


    }
}