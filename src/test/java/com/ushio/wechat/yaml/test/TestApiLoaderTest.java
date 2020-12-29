package com.ushio.wechat.yaml.test;

import com.ushio.wechat.helper.ApiLoader;
import com.ushio.wechat.util.Constant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 * 封装ApiLoad工具类，在BeforeAll里面初始化得到所有的ObjectModel集合
 * 再通过name和actions里的key查找到固定的ActionModel，再执行run()方法
 * 缺陷：参数和ObjectModel对象的属性都需要手动传入，但是ActionModel的属性不用
 **/
public class TestApiLoaderTest {

    @BeforeAll
    public static void initObjList(){
        ApiLoader.load("src/test/resources/api");
    }

    @Test
    void runTest(){
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add(Constant.CORPID);
        actualParameter.add(Constant.CORPSECRET);
        ApiLoader.loadActionModel("tokenhelper","getToken")
                .run(actualParameter);
    }
}
