package com.ushio.wechat.pro.test;

import com.ushio.wechat.pro.api.DepartmentApi;
import com.ushio.wechat.util.RandomUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

/**
 * @author: ushio
 * @description: 模拟多次创建过程，使用线程ID+时间戳方式避免部门名字重复
 **/
public class DempRepeat {

    @DisplayName("创建部门")
    @RepeatedTest(3)
    @Execution(CONCURRENT)
    void createDepartmentRepeated() {
        String backendStr = Thread.currentThread().getId()+ RandomUtil.getTimeStamp()+"";
        String name = "name"+ backendStr;
        String enName = "en_name"+backendStr;
        Response response = DepartmentApi.createDepartment(name,enName);
        assertEquals("0",response.path("errcode").toString());
    }

}
