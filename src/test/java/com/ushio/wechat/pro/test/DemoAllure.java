package com.ushio.wechat.pro.test;

import com.ushio.wechat.pro.api.DepartmentApi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import com.ushio.wechat.util.RandomUtil;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * allure2注解举例
 */
@Epic("Epic企业微信接口测试用例")
@Feature("Feature部门相关功能测试")
public class DemoAllure {

    @AfterEach
    @BeforeEach
    void clearDepartment() {
        DepartmentApi.clearDpartment();
    }

    @Description("这个测试方法会测试创建部门的功能-入参数据驱动")
    @Story("创建部门测试")
    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String creatName, String creatEnName, String returnCode) {
        Response creatResponse = DepartmentApi.createDepartment(creatName, creatEnName);
        assertEquals(returnCode, creatResponse.path("errcode").toString());
    }

    @Description("这个测试方法会测试修改部门的功能")
    @Story("修改部门测试")
    @DisplayName("修改部门")
    @Test
    void updateDepartment() {
        String departmentId = DepartmentApi.createDepartmentByTimeStamp();

        String updateName = "upname" + RandomUtil.getTimeStamp();
        String updateEnName = "en_upname" + RandomUtil.getTimeStamp();

        Response updateResponse = DepartmentApi.updateDepartment(updateName, updateEnName,departmentId);
        assertEquals("0", updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Description("这个测试方法会测试查询部门的功能")
    @Story("查询部门测试")
    @Test
    void listDepartment() {
        String creatName = "name" + RandomUtil.getTimeStamp();
        String creatEnName = "en_name" + RandomUtil.getTimeStamp();

        Response creatResponse = DepartmentApi.createDepartment(creatName, creatEnName);
        String departmentId = creatResponse.path("id") != null ? creatResponse.path("id").toString() : null;

        Response listResponse = DepartmentApi.listDepartment(departmentId);

        assertAll("返回值校验！",
                () -> assertEquals("0", listResponse.path("errcode").toString()),
                () -> assertEquals(departmentId, listResponse.path("department.id[0]").toString()),
                () -> assertEquals(creatName, listResponse.path("department.name[0]").toString()),
                () -> assertEquals(creatEnName, listResponse.path("department.name_en[0]").toString())

        );
    }
    @DisplayName("删除部门")
    @Description("这个测试方法会测试删除部门的功能")
    @Story("删除部门测试")
    @Test
    void deleteDepartment() {
        String departmentId = DepartmentApi.createDepartmentByTimeStamp();

        Response deletResponse = DepartmentApi.deleteDepartment(departmentId);

        assertEquals("0", deletResponse.path("errcode").toString());
    }
}
