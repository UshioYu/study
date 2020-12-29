package com.ushio.wechat.yaml.model;

import com.ushio.wechat.util.Constant;
import com.ushio.wechat.util.PlaceholderUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

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

    public Response run(ArrayList<String> actualParameter) {
        HashMap<String,String> tempParam  = new HashMap<>();
        HashMap<String, String> actionVariables = Constant.globalVariables;
        String tempUrl = getUrl();
        String tempBody = getBody();
        //根据get和set值来判断请求方法
        if (!"".equals(getGet())) {
            method = "get";
            tempUrl = getGet();
        } else if (!"".equals(getPost())) {
            method = "post";
            tempUrl = getPost();
        }

        if (formalParam != null && formalParam.length > 0 &&
                actualParameter != null && actualParameter.size() > 0) {
            //形参和实参构建map
            for (int i = 0; i < formalParam.length; i++)
                actionVariables.put(formalParam[i], actualParameter.get(i));
        }

        //对url、query和body这三个可能出现${}符号的字符进行替换，
        // 我猜query和body二选一吧（下面有验证）
        if (getQuery() != null) {
            //这里的方法保证getQuery()如果有多的数据，会保留
            tempParam.putAll(PlaceholderUtils.resolveMap(getQuery(), actionVariables));
        }

        if (!"".equals(tempBody)) {
            //这里的方法保证tempBody如果有多的数据，会保留，比如"parentid": 1
            tempBody = PlaceholderUtils.resolveString(tempBody, actionVariables);
        }

        //url上可能也有${}
        tempUrl = PlaceholderUtils.resolveString(tempUrl, actionVariables);

        //开始请求，并把结果进行返回
        RequestSpecification requestSpecification = given().log().all();
        if (!"".equals(getContentType())) {
            requestSpecification.contentType(getContentType());
        }
        if (headers != null) {
            requestSpecification.headers(headers);
        }
        if (tempParam != null && tempParam.size() > 0) {
            requestSpecification.formParams(tempParam);
        } else if (!"".equals(tempBody)) {
            requestSpecification.body(tempBody);
        }

        return requestSpecification.request(method,tempUrl)
                .then().log().all().extract().response();
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
