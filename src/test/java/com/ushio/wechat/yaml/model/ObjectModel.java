package com.ushio.wechat.yaml.model;

import java.util.HashMap;

/**
 * @author: ushio
 * @description:
 **/
public class ObjectModel {
    private String name;
    private HashMap<String , ActionModel> actions;
    private HashMap<String,String> obVariables;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public HashMap<String , ActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String , ActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getObVariables() {
        return obVariables;
    }

    public void setObVariables(HashMap<String, String> obVariables) {
        this.obVariables = obVariables;
    }

    @Override
    public String toString() {
        return "ApiObjectModel{" +
                "name='" + name + '\'' +
                ", actions=" + actions +
                ", obVariables=" + obVariables +
                '}';
    }
}
