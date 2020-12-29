package com.ushio.wechat.yaml.model;

import com.ushio.wechat.helper.ApiLoader;
import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.util.PlaceholderUtils;
import com.ushio.wechat.yaml.result.StepResult;
import io.restassured.response.Response;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author: ushio
 * @description:
 **/
public class StepModel {
    private String api;
    private String action;
    private String[] actualParameter;
    private HashMap<String,String> saveGlobal;
    private HashMap<String,String> save;
    private ArrayList<AssertModel> asserts;

    public String getApi() {
        return api == null ? "" : api;
    }

    public void setApi(String api) {
        this.api = api == null ? "" : api;
    }

    public String getAction() {
        return action == null ? "" : action;
    }

    public void setAction(String action) {
        this.action = action == null ? "" : action;
    }

    public String[] getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(String[] actualParameter) {
        this.actualParameter = actualParameter;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public StepResult run(HashMap<String, String> testCaseVariables) {
        ArrayList<String> tempParam = new ArrayList<>();
        //参数放入临时list里
        if (actualParameter != null) {
            ArrayList<String> tempVariables = new ArrayList<>();
            tempVariables.addAll(Arrays.asList(actualParameter));
            tempParam.addAll(PlaceholderUtils.resolveList(tempVariables, testCaseVariables));
        }

        //通过api+action锁定请求，先打出请求结果
        Response response = ApiLoader.loadActionModel(api,action).run(tempParam);

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
        if (saveGlobal != null) {
            saveGlobal.forEach((variablesName, path) -> {
                String value = response.path(path).toString();
                Constant.globalVariables.put(variablesName, value);
                LogHelper.info("全局更新：variablesName：" + variablesName
                        + ",value:" + value);
            });
        }

        //根据case中的配置对返回结果进行软断言，但不会终结测试将断言结果存入断言结果列表中，在用例最后进行统一结果输出
        ArrayList<Executable> assertList = new ArrayList<>();
        if (asserts != null) {
            asserts.stream().forEach(assertModel -> {
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

    @Override
    public String toString() {
        return "StepModel{" +
                "api='" + api + '\'' +
                ", action='" + action + '\'' +
                ", actualParameter=" + Arrays.toString(actualParameter) +
                ", saveGlobal=" + saveGlobal +
                ", save=" + save +
                ", asserts=" + asserts +
                '}';
    }
}
