package com.ushio.wechat.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.ushio.wechat.yaml.model.ActionModel;
import com.ushio.wechat.yaml.model.ApiTestCaseModel;
import com.ushio.wechat.yaml.model.ObjectModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ushio
 * @description:
 **/
public class ApiLoader {

    /**
     * 加载所有api Object对象，并保存到本列表中
     */
    private static List<ObjectModel> objectModelList = new ArrayList<>();

    public static void load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path -> {
            try {
                objectModelList.add(loadObjModel(dir + "/" + path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static ObjectModel loadObjModel(String path) {
        ObjectModel objectModel = new ObjectModel();
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            objectModel = mapper.readValue(new File(path), ObjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objectModel;
    }

    public static ApiTestCaseModel loadTestCaseModel(String path){
        ApiTestCaseModel apiTestCaseModel = new ApiTestCaseModel();
        try {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            apiTestCaseModel =  objectMapper.readValue(new File(path),ApiTestCaseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  apiTestCaseModel;
    }

    public static ActionModel loadActionModel(String name, String actionName){
        ActionModel actionModel = new ActionModel();

        try {
            for (ObjectModel objectModel : objectModelList) {
                if (name != null && objectModel.getName() != null
                        && name.equals(objectModel.getName())) {
                    actionModel = objectModel.getActions().get(actionName);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return actionModel;
    }

}
