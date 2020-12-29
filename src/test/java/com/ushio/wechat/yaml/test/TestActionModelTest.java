package com.ushio.wechat.yaml.test;

import com.ushio.wechat.util.Constant;
import com.ushio.wechat.yaml.model.ActionModel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author: ushio
 * @description:
 * 定义一个ActionModel，对应yaml的actions下的object
 * 可以理解为一个最小的方法obj，通过给它传参的方式去最后调用run()方法
 * 模拟实现一次请求，实际还是通过RequestSpecification发了一次请求
 * 缺陷：参数和对象ActionModel的属性都需要手动传入
 **/
public class TestActionModelTest {

    @Test
    void runTest(){
        ActionModel model = new ActionModel();
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add(Constant.CORPID);
        actualParameter.add(Constant.CORPSECRET);
        model.setUrl(Constant.DOMAIN_URL + "/${x}");
        Constant.globalVariables = new HashMap<>();
        Constant.globalVariables.put("x","gettoken");

        String[] formalParameter = new String[2];
        formalParameter[0] = "corpid";
        formalParameter[1] = "corpsecret";
        model.setFormalParam(formalParameter);

        HashMap<String ,String> query = new HashMap<>();
        query.put("corpid","${corpid}");
        query.put("corpsecret","${corpsecret}");
        model.setQuery(query);

        model.run(actualParameter);
    }
}
