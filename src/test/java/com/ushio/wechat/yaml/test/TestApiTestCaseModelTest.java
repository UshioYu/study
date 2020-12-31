package com.ushio.wechat.yaml.test;

import com.ushio.wechat.yaml.api.LoadApi;
import com.ushio.wechat.yaml.model.ApiTestCaseModel;
import com.ushio.wechat.yaml.api.ModelRunApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author: ushio
 * @description:
 * 这里通过ObjectMapper传入固定yaml文件获取ApiTestCaseModel对象，
 * 调用runApiTestCaseModel()方法：这里通过遍历steps获取每个请求的StepResult，并在最后对结果集进行统一断言
 * 优点：参数和Model类声明的属性都可以不用手动输入(参数在testcase下yaml里面带入,复用了yaml文件里面属性)
 **/
public class TestApiTestCaseModelTest {

    @BeforeAll
    public static void initObjList(){
        LoadApi.load("src/test/resources/api");
    }

    @Test
    void runTest(){
        ApiTestCaseModel apiTestCaseModel = LoadApi.loadTestCaseModel("src/test/resources/testcase/creatdepartment.yaml");
        ModelRunApi.runApiTestCaseModel(apiTestCaseModel);
    }
}
