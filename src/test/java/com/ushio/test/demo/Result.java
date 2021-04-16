package com.ushio.test.demo;

/**
 * @author: ushio
 * @description:
 **/
public class Result {

    private String name;
    private boolean result;

    public String getName() {
        return name == null ? "" : name;
    }

    public boolean isResult() {
        return result;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", result=" + result +
                '}';
    }

}
