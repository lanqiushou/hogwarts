/**
 * projectName: WeChatWorkApiTest
 * fileName: EvnTask.java
 * packageName: com.wechat.task
 * date: 2020-07-18 5:47 下午
 */
package test_service.task;

import test_service.wechat_apiobject.DepartmentApiObject;
import io.restassured.response.Response;
import test_service.wechat_apiobject.UserApiObject;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: EvnTask
 * @packageName: com.wechat.task
 * @description: 环境管理任务
 * @data: 2020-07-18 5:47 下午
 **/
public class EvnTask {
    public static void clearDepartment(String accessToken) {
        Response listResponse = DepartmentApiObject.listDepartMent("", accessToken);
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for (int departmentId : departmentIdList) {
            if (1 == departmentId) {
                continue;
            }
            DepartmentApiObject.deleteDepartMent(departmentId + "", accessToken);
        }
    }

    public static void clearUser(String accessToken, int departmentId, int fetchChild) {
        Response listResponse = UserApiObject.listUser(accessToken, departmentId, fetchChild);
        ArrayList<Object> userIdList = listResponse.path("userlist.userid");

        //有个用户是企业微信的初始创建用户，不可以删除
        userIdList.remove("TengJingBao");

        UserApiObject.batchDeleteUser(accessToken, userIdList);
    }
}