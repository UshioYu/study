package com.ushio.wechat.yaml.test;

import com.ushio.wechat.helper.ApiLoader;
import com.ushio.wechat.util.Constant;
import com.ushio.wechat.yaml.model.ObjectModel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 * 这里通过加载固定路径的yaml文件找到对应的ObjectModel(这里ObjectModel)
 * 通过Map的key找到对应的ActionModel，再执行run(此时除了ID和SECRET
 * 传参无需再给ActionModel设置其他属性，复用了yaml文件里面属性)
 * 核心就是ObjectMapper读取文件路径强转成Class对象
 * 缺陷：参数和ObjectModel对象的属性都需要手动传入，但是ActionModel的属性不用
 **/
public class TestObjectModelTest {

    @Test
    void runTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add(Constant.CORPID);
        actualParameter.add(Constant.CORPSECRET);
        ObjectModel objectModel = ApiLoader.loadObjModel("src/test/resources/api/tokenhelper.yaml");
        objectModel.getActions().get("getToken").run(actualParameter);

    }
}
