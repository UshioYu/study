package com.ushio.wechat.yaml.test;

import com.ushio.wechat.yaml.api.LoadApi;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.yaml.model.ActionModel;
import com.ushio.wechat.yaml.model.ObjectModel;
import com.ushio.wechat.yaml.api.ModelRunApi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 * 这里通过加载固定路径的yaml文件找到对应的ObjectModel
 * 通过actions的key找到对应的ActionModel，再执行runActionModel()
 * 核心就是ObjectMapper读取文件路径强转成Class对象
 * 缺陷：参数和actions的key都需要手动传入，但是ActionModel的属性不用
 **/
public class TestObjectModelTest {

    @Test
    void runTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add(Constant.CORPID);
        actualParameter.add(Constant.CORPSECRET);
        ObjectModel objectModel = LoadApi.loadObjModel("src/test/resources/api/tokenhelper.yaml");
        ActionModel actionModel = objectModel.getActions().get("getToken");
        ModelRunApi.runActionModel(actionModel,actualParameter);

    }
}
