package com.ushio.wechat.pro.api;

import com.ushio.wechat.util.Constant;
import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.util.RandomUtil;
import com.ushio.wechat.helper.TokenHelper;
import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * 部门api，功能包括增删改查
 */
public class DepartmentApi {

    public static Response createDepartment(String name,String enName){
        String body = "{\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"name_en\": \"" + enName + "\",\n" +
                "   \"parentid\": 1}";//todo 这里id为什么是1
        return given().log().all()
                .contentType("application/json")
                .body(body)
                .post(Constant.CREATE_DEPARTMENT_URL + "?access_token=" + TokenHelper.getAccessToken())
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response createDepartmentByTimeStampRes() {
        String creatName = "name" + RandomUtil.getTimeStamp();
        String creatEnName = "en_name" + RandomUtil.getTimeStamp();
        return DepartmentApi.createDepartment(creatName, creatEnName);
    }

    public static String createDepartmentByTimeStamp() {
        Response response = createDepartmentByTimeStampRes();
        return response.path("id") != null ? response.path("id").toString() : null;
    }

    public static String creatEDepartMentByRandomInt() {
        String creatName = "name" + RandomUtil.getRandomInt(1000);
        String creatEnName = "en_name" + RandomUtil.getRandomInt(1000);
        Response response = DepartmentApi.createDepartment(creatName, creatEnName);
        String departmentId = response.path("id") != null ? response.path("id").toString() : null;
        LogHelper.info(departmentId);
        return departmentId;
    }

    public static Response updateDepartment(String updateName,String updateEnName,String departmentId){
        String body ="{\n" +
                "   \"id\": "+departmentId+",\n" +
                "   \"name\": \""+updateName+"\",\n" +
                "   \"name_en\": \""+updateEnName+"\",\n" +
                //todo 这里order为何为1,在父部门中的次序值。order值大的排序靠前。有效的值范围是[0, 2^32)
                "   \"order\": 1\n" +
                "}\n";
        return given().log().all()
                .contentType("application/json")
                .body(body)
                .post(Constant.UPDATE_DEPARTMENT_URL + "?access_token=" + TokenHelper.getAccessToken())
                .then()
                .log().body()
                .extract().response();
    }

    public static Response listDepartment(String departmentId){
        return given().log().all()
                .when()
                .param("id",departmentId)
                .get(Constant.LIST_DEPARTMENT_URL + "?access_token=" + TokenHelper.getAccessToken())
                .then()
                .log().body()
                .extract()
                .response();
    }

    public static Response deleteDepartment(String departmentId){
        return given().log().all()
                .contentType("application/json")
                .param("access_token",TokenHelper.getAccessToken())
                .param("id",departmentId)
                .get(Constant.DELETE_DEPARTMENT_URL)
                .then()
                .log().body()
                .extract().response();
    }

    public static void clearDpartment(){

        ArrayList<Integer> departmentIds = listDepartment("").path("department.id");
        for (int departmentId : departmentIds) {
            if (1 == departmentId)
                continue;
            deleteDepartment(departmentId + "");
        }
    }
}
