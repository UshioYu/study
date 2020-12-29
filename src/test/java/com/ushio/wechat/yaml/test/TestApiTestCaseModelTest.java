package com.ushio.wechat.yaml.test;

import com.ushio.wechat.helper.ApiLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author: ushio
 * @description:
 * 这里通过ObjectMapper传入固定yaml文件获取ApiTestCaseModel对象，
 * 调用run（）方法：这里通过遍历steps获取每个请求的StepResult，并在最后对结果集进行统一断言
 * 缺陷：参数和Model类声明的属性都可以不用手动输入
 **/
public class TestApiTestCaseModelTest {

    @BeforeAll
    public static void initObjList(){
        ApiLoader.load("src/test/resources/api");
    }

    @Test
    void runTest(){
        ApiLoader.loadTestCaseModel("src/test/resources/testcase/creatdepartment.yaml").run();
    }
}
