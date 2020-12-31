package com.ushio.wechat.yaml.test;

import com.ushio.wechat.yaml.api.LoadApi;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.yaml.model.ActionModel;
import com.ushio.wechat.yaml.api.ModelRunApi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 * 封装LoadApi工具类，在BeforeAll里面初始化得到所有的ObjectModel集合
 * 再通过name、actionName查找到固定的ActionModel，再执行runActionModel()方法
 * 缺陷：参数和name、actionName都需要手动传入，但是ActionModel的属性不用
 **/
public class TestLoadApiTest {

    @BeforeAll
    public static void initObjList(){
        LoadApi.load("src/test/resources/api");
    }

    @Test
    void runTest(){
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add(Constant.CORPID);
        actualParameter.add(Constant.CORPSECRET);
        ActionModel actionModel = LoadApi.loadActionModel("tokenhelper", "getToken");
        ModelRunApi.runActionModel(actionModel, actualParameter);
    }
}
