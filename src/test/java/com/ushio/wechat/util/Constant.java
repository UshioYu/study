package com.ushio.wechat.util;

import java.util.HashMap;

public class Constant {

    //用户信息
    public static final String CORPID = "ww681594090a0d29e1";
    public static final String CORPSECRET = "JDF9hU47wIBx44oLxt0ksKWfQTpIAHnlnHhTdM-1ZPM";

    //接口地址
    public static final String DOMAIN_URL = "https://qyapi.weixin.qq.com/cgi-bin";
    public static final String GET_TOKEN_URL = DOMAIN_URL + "/gettoken";
    public static final String CREATE_DEPARTMENT_URL = DOMAIN_URL + "/department/create";
    public static final String UPDATE_DEPARTMENT_URL = DOMAIN_URL + "/department/update";
    public static final String DELETE_DEPARTMENT_URL = DOMAIN_URL + "/department/delete";
    public static final String LIST_DEPARTMENT_URL = DOMAIN_URL + "/department/list";

    //存储全局变量，这里具体主要就是token(yaml里面的saveGlobal是存储目标)
    public static HashMap<String, String> globalVariables = new HashMap<>();

}
