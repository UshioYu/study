package com.ushio.wechat.yaml.test;

import com.ushio.wechat.yaml.api.LoadApi;
import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.yaml.model.ApiTestCaseModel;
import com.ushio.wechat.yaml.api.ModelRunApi;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author: ushio
 * @description:
 **/
public class TestParameterizedTest {

    @ParameterizedTest(name = "{index} {1}")
    @MethodSource
    void apiTest(ApiTestCaseModel apiTestCaseModel, String name, String description) {
        LogHelper.info("【用例开始执行】");
        LogHelper.info("用例名称： " + name);
        LogHelper.info("用例描述： " + description);
        ModelRunApi.runApiTestCaseModel(apiTestCaseModel);
    }

    static List<Arguments> apiTest() {

        LoadApi.load("src/test/resources/api");

        //用来传递给参数化用例
        List<Arguments> testcases = new ArrayList<>();

        //读取所有的测试用例
        String testcaseDir = "src/test/resources/testcase";
        /**
         * 1、遍历目录下所有的用例文件，并组装成参数列表
         */
        Arrays.stream(new File(testcaseDir).list())
                .forEach(name -> {
                    String path = testcaseDir + "/" + name;
                    try {
                        ApiTestCaseModel apiTestCase = LoadApi.loadTestCaseModel(path);
                        testcases.add(arguments(apiTestCase, apiTestCase.getName(), apiTestCase.getDescription()));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return testcases;
    }
}
