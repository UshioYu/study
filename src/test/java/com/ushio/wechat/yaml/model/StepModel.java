package com.ushio.wechat.yaml.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
