package com.ushio.wechat.yaml.model;

import java.util.ArrayList;

/**
 * @author: ushio
 * @description:
 **/
public class ApiTestCaseModel {

    private String name;
    private String description;
    private ArrayList<StepModel> steps;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description;
    }

    public ArrayList<StepModel> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModel> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return "ApiTestCaseModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", steps=" + steps +
                '}';
    }

}
