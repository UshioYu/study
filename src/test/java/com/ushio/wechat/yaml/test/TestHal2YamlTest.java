package com.ushio.wechat.yaml.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ushio.wechat.helper.LogHelper;
import com.ushio.wechat.util.BaseUtil;
import com.ushio.wechat.yaml.model.ActionModel;
import com.ushio.wechat.yaml.model.ObjectModel;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.HarReaderException;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarRequest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: ushio
 * @description:
 * 通过hal文件（浏览器里面请求然后F12调试可生成）
 * 生成ObjectModel后再输出成yaml
 **/
public class TestHal2YamlTest {

    @Test
    void hal2YamlTest() throws HarReaderException, IOException {
        HarReader harReader = new HarReader();
        Har har = harReader.readFromFile(new File("src/test/resources/har/qyapi.weixin.qq.com.har"));
        LogHelper.info("create a har");

        ObjectModel objectModel =  new ObjectModel();
        ActionModel actionModel = new ActionModel();
        HashMap<String, ActionModel> actions = new HashMap<>();
        HashMap<String,String> queryMap = new HashMap<>();
        har.getLog().getEntries().forEach(entrie->{
            HarRequest harRequest = entrie.getRequest();
            harRequest.getQueryString().forEach(query->{
                queryMap.put(query.getName(),query.getValue());
            });
            String method = harRequest.getMethod().toString();
            String url = harRequest.getUrl();
            actionModel.setQuery(queryMap);
            if(method.equals("get")){
                actionModel.setGet(url);
            }else{
                actionModel.setPost(url);
            }
            actions.put(BaseUtil.getRequestName(url),actionModel);
        });
        //这里"tokenhelper1"是随便命名的，就是声明xml里面的name字段值
        objectModel.setName("tokenhelper1");
        objectModel.setActions(actions);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("src/test/resources/har/tokenhelper1.yaml"),objectModel);
    }


}
