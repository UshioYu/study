package com.ushio.wechat.pro.test;

import com.ushio.wechat.pro.api.DepartmentApi;
import com.ushio.wechat.util.RandomUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: ushio
 * @description: 列出list，用lambda表达式断言
 **/
public class DemoLambda {

    @DisplayName("查询新建的部门")
    @Test
    void listDepartmentById() {
        String creatName= "name"+ RandomUtil.getTimeStamp();
        String creatEnName="en_name"+ RandomUtil.getTimeStamp();
        Response creatResponse = DepartmentApi.createDepartment(creatName,creatEnName);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;

        Response listResponse = DepartmentApi.listDepartment(departmentId);

        assertAll("查询返回值校验！",
                ()->assertEquals("0",listResponse.path("errcode").toString()),
                ()->assertEquals(departmentId,listResponse.path("department.id[0]").toString()),
                ()->assertEquals(creatName,listResponse.path("department.name[0]").toString()),
                ()->assertEquals(creatEnName,listResponse.path("department.name_en[0]").toString())
        );

    }

    @DisplayName("查询部门")
    @Test
    void listDepartment() {
        Response listResponse =DepartmentApi.listDepartment("");
        assertAll("查询返回值校验！",
                ()->assertEquals("0",listResponse.path("errcode").toString())
                ,()->assertEquals("xxxxxxx",listResponse.path("department.name[0]").toString())
        );

    }

}
