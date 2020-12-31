package com.ushio.wechat.yaml.api;

import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.util.PlaceholderUtils;
import com.ushio.wechat.util.RandomUtil;
import com.ushio.wechat.yaml.model.ActionModel;
import com.ushio.wechat.yaml.model.ApiTestCaseModel;
import com.ushio.wechat.yaml.model.StepModel;
import com.ushio.wechat.yaml.result.StepResult;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @author: ushio
 * @description:提取各个model类的run()方法
 **/
public class ModelRunApi {

    /**
     * 用actionModel模拟实现一次请求，实际还是通过RequestSpecification发了一次请求
     * @param actionModel
     * @param actualParameter
     * @return
     */
    public static Response runActionModel(ActionModel actionModel,ArrayList<String> actualParameter) {
        HashMap<String,String> tempParam  = new HashMap<>();
        HashMap<String, String> actionVariables = Constant.globalVariables;
        String tempUrl = actionModel.getUrl();
        String tempBody = actionModel.getBody();
        //根据get和set值来判断请求方法
        if (!"".equals(actionModel.getGet())) {
            actionModel.setMethod("get");
            tempUrl = actionModel.getGet();
        } else if (!"".equals(actionModel.getPost())) {
            actionModel.setMethod("post");
            tempUrl = actionModel.getPost();
        }

        if (actionModel.getFormalParam() != null && actionModel.getFormalParam().length > 0 &&
                actualParameter != null && actualParameter.size() > 0) {
            //形参和实参构建map
            for (int i = 0; i < actionModel.getFormalParam().length; i++)
                actionVariables.put(actionModel.getFormalParam()[i], actualParameter.get(i));
        }

        //对url、query和body这三个可能出现${}符号的字符进行替换，
        // 我猜query和body二选一吧（下面有验证）
        if (actionModel.getQuery() != null) {
            //这里的方法保证getQuery()如果有多的数据，会保留
            tempParam.putAll(PlaceholderUtils.resolveMap(actionModel.getQuery(), actionVariables));
        }

        if (!"".equals(tempBody)) {
            //这里的方法保证tempBody如果有多的数据，会保留，比如"parentid": 1
            tempBody = PlaceholderUtils.resolveString(tempBody, actionVariables);
        }

        //url上可能也有${}
        tempUrl = PlaceholderUtils.resolveString(tempUrl, actionVariables);

        //开始请求，并把结果进行返回
        RequestSpecification requestSpecification = given().log().all();
        if (!"".equals(actionModel.getContentType())) {
            requestSpecification.contentType(actionModel.getContentType());
        }
        if (actionModel.getHeaders() != null) {
            requestSpecification.headers(actionModel.getHeaders());
        }
        if (tempParam != null && tempParam.size() > 0) {
            requestSpecification.formParams(tempParam);
        } else if (!"".equals(tempBody)) {
            requestSpecification.body(tempBody);
        }

        return requestSpecification.request(actionModel.getMethod(),tempUrl)
                .then().log().all().extract().response();
    }

    /**
     * 实际上就是把参数传入，根据StepModel属性设置api和action，
     * 通过LoaderApi方法调用获取结果Response，最后对结果集Respose进行封装成
     * StepResult对象来输出。
     * @param stepModel
     * @param testCaseVariables
     * @return
     */
    public static StepResult runStepModel(StepModel stepModel,HashMap<String, String> testCaseVariables) {
        ArrayList<String> tempParam = new ArrayList<>();
        //参数放入临时list里
        if (stepModel.getActualParameter() != null) {
            ArrayList<String> tempVariables = new ArrayList<>();
            tempVariables.addAll(Arrays.asList(stepModel.getActualParameter()));
            tempParam.addAll(PlaceholderUtils.resolveList(tempVariables, testCaseVariables));
        }

        //通过api+action锁定请求，先打出请求结果
        ActionModel actionModel = LoadApi.loadActionModel(stepModel.getApi(), stepModel.getAction());
        Response response = runActionModel(actionModel, tempParam);

        //存储save的值到一个map（这个map当做是StepResult的变量）
        //这里先注释，因为发现这个变量无调用处
//        HashMap<String,String> stepVariables = new HashMap<>();
//        if (save != null) {
//            save.forEach((variablesName, path) -> {
//                String value = response.path(path).toString();
//                stepVariables.put(variablesName, value);
//                LogHelper.info("step变量更新：variablesName：" + variablesName
//                        + ",value:" + value);
//            });
//        }

        //存储全局saveGlobal的值到静态变量
        if (stepModel.getSaveGlobal() != null) {
            stepModel.getSaveGlobal().forEach((variablesName, path) -> {
                String value = response.path(path).toString();
                Constant.globalVariables.put(variablesName, value);
                LogHelper.info("全局更新：variablesName：" + variablesName
                        + ",value:" + value);
            });
        }

        //根据case中的配置对返回结果进行软断言，但不会终结测试将断言结果存入断言结果列表中，在用例最后进行统一结果输出
        ArrayList<Executable> assertList = new ArrayList<>();
        if (stepModel.getAsserts() != null) {
            stepModel.getAsserts().stream().forEach(assertModel -> {
                assertList.add(()->{
                    assertThat(assertModel.getReason(),
                            response.path(assertModel.getActual()).toString(),
                            equalTo(assertModel.getExpect()));
                });
            });
        }

        //返回结果通过StepResult吐出去
        StepResult stepResult = new StepResult();
        stepResult.setAssertList(assertList);
        stepResult.setResponse(response);
        return stepResult;
    }

    /**
     * 这里通过遍历steps获取每个请求的StepResult，并在最后对结果集进行统一断言
     * @param apiTestCaseModel
     */
    public static void runApiTestCaseModel(ApiTestCaseModel apiTestCaseModel){
        ArrayList<Executable> assertList = new ArrayList<>();
        HashMap<String, String> testCaseVariables = new HashMap<>();
        //加载用例层关键字变量
        testCaseVariables.put("getTimeStamp", RandomUtil.getTimeStamp());
        LogHelper.info("用例变量更新： " + testCaseVariables);
        //遍历steps进行执行
        if (apiTestCaseModel.getSteps() != null) {
            apiTestCaseModel.getSteps().forEach(step -> {
                StepResult stepResult = runStepModel(step, testCaseVariables);
                //处理step返回的save变量，这步感觉没啥用
//                if(stepResult.getStepVariables().size()>0){
//                    testCaseVariables.putAll(stepResult.getStepVariables());
//                    LogHelper.info("testcase变量更新： "+ testCaseVariables);
//                }
                //处理assertList,并进行统一断言
                if (stepResult.getAssertList().size() > 0) {
                    assertList.addAll(stepResult.getAssertList());
                }
            });
            //5、进行统一断言
            assertAll("",assertList.stream());
        }
    }

}
