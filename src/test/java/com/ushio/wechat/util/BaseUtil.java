package com.ushio.wechat.util;

/**
 * @author: ushio
 * @description:
 **/
public class BaseUtil {

    public static String getRequestName(String url) {
        String[] suburl = url.split("\\u003F")[0].split("/");
        String name = "";
        if (suburl.length > 1) {
            name = suburl[suburl.length - 1];
        }else if(1==suburl.length){
            name = suburl[0];
        }
        return name;
    }
}
