package com.ushio.wechat.pro.test;

import com.ushio.wechat.pro.api.DepartmentApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: ushio
 * @description: 通过外部csv文件创建部门信息
 **/
public class DemoCsv {

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String creatName, String creatEnName, String returnCode) {
        Response response = DepartmentApi.createDepartment(creatName, creatEnName);
        assertEquals(returnCode, response.path("errcode").toString());
    }

}
