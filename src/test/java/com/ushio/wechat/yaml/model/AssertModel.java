package com.ushio.wechat.yaml.model;

/**
 * @author: ushio
 * @description:
 **/
public class AssertModel {
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    public String getActual() {
        return actual == null ? "" : actual;
    }

    public void setActual(String actual) {
        this.actual = actual == null ? "" : actual;
    }

    public String getMatcher() {
        return matcher == null ? "" : matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher == null ? "" : matcher;
    }

    public String getExpect() {
        return expect == null ? "" : expect;
    }

    public void setExpect(String expect) {
        this.expect = expect == null ? "" : expect;
    }

    public String getReason() {
        return reason == null ? "" : reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? "" : reason;
    }

    @Override
    public String toString() {
        return "AssertModel{" +
                "actual='" + actual + '\'' +
                ", matcher='" + matcher + '\'' +
                ", expect='" + expect + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
