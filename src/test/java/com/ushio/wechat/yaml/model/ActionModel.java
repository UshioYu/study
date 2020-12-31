package com.ushio.wechat.yaml.model;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author: ushio
 * @description:
 **/
public class ActionModel {
    private String[] formalParam;
    private HashMap<String,String> headers;
    private String body;
    private String method = "get";
    private String post;
    private String get;
    private String url;
    private String contentType;
    private HashMap<String,String> query;

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url == null ? "" : url;
    }

    public String[] getFormalParam() {
        return formalParam;
    }

    public void setFormalParam(String[] formalParam) {
        this.formalParam = formalParam;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body == null ? "" : body;
    }

    public void setBody(String body) {
        this.body = body == null ? "" : body;
    }

    public String getMethod() {
        return method == null ? "" : method;
    }

    public void setMethod(String method) {
        this.method = method == null ? "" : method;
    }

    public String getPost() {
        return post == null ? "" : post;
    }

    public void setPost(String post) {
        this.post = post == null ? "" : post;
    }

    public String getGet() {
        return get == null ? "" : get;
    }

    public void setGet(String get) {
        this.get = get == null ? "" : get;
    }

    public String getContentType() {
        return contentType == null ? "" : contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? "" : contentType;
    }

    public HashMap<String, String> getQuery() {
        return query;
    }

    public void setQuery(HashMap<String, String> query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "ApiActionModel{" +
                "formalParam=" + Arrays.toString(formalParam) +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                ", method='" + method + '\'' +
                ", post='" + post + '\'' +
                ", get='" + get + '\'' +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                ", query=" + query +
                '}';
    }

}
