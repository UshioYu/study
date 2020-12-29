package com.ushio.wechat.yaml.test;

import com.ushio.wechat.helper.ApiLoader;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.yaml.model.AssertModel;
import com.ushio.wechat.yaml.model.StepModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: ushio
 * @description:
 * 定义StepModel，通过给其设置各种属性（主要是断言）,最后调用run（）方法，
 * 这里调用run()方法实际上就是把参数传入，根据StepModel属性设置api和action，
 * 通过ApiLoader方法调用获取结果Response，最后对结果集Respose进行封装成
 * StepResult对象来输出。
 * 缺陷：参数和AssertModel对象的属性都需要手动传入，但是ObjectModel和ActionModel的属性不用
 * 其实是把原先ObjectModel的属性api和action变相由AssertModel给出
 **/
public class TestStepModelTest {

    @BeforeAll
    public static void initObjList(){
        ApiLoader.load("src/test/resources/api");
    }

    @Test
    void runTest(){
        String[] actualParameter = new String[2];
        actualParameter[0] = Constant.CORPID;
        actualParameter[1] = Constant.CORPSECRET;
        //断言
        ArrayList<AssertModel> asserts = new ArrayList<>();
        AssertModel assertModel = new AssertModel();
        assertModel.setActual("errcode");
        assertModel.setExpect("2");
        assertModel.setMatcher("equalTo");
        assertModel.setReason("getToken错误码校验01");
        asserts.add(assertModel);
        //save
        HashMap<String ,String> save = new HashMap<>();
        save.put("accesstoken","access_token");
        //saveGlobal
        HashMap<String ,String> saveGlobal = new HashMap<>();
        saveGlobal.put("accesstoken","access_token");

        StepModel stepModel = new StepModel();
        stepModel.setActualParameter(actualParameter);
        stepModel.setSave(save);
        stepModel.setSaveGlobal(saveGlobal);
        stepModel.setAsserts(asserts);
        stepModel.setApi("tokenhelper");
        stepModel.setAction("getToken");
        stepModel.run(null);
    }
}
