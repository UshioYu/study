package com.ushio.wechat.util;

import com.ushio.wechat.helper.LogHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ushio
 * @description:配置文件或模板中的占位符替换工具类
 **/
public class PlaceholderUtils {
    public static final String PLACEHOLDER_PREFIX = "${";
    public static final String PLACEHOLDER_SUFFIX = "}";

    /**
     * 从${key1},${key2}中取出key1和key2，并从parameter中匹配相同的key，最后输出value替换原text
     */
    public static String resolveString(String text, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || text == null || text.isEmpty()) {
            return text;
        }
        StringBuffer buf = new StringBuffer(text);
        /**
         * 计算变量名开始位置
         **/
        int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
        while (startIndex != -1) {
            /**
             * 计算变量名结束的位置
             **/
            int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
            if (endIndex != -1) {
                /**
                 * 取出要替换的变量名
                 **/
                String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
                int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
                try {
                    /**
                     * 取出变量map中的真实值
                     **/
                    String propVal = parameter.get(placeholder);
                    if (propVal != null) {
                        /**
                         * 替换变量
                         **/
                        buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), propVal);
                        nextIndex = startIndex + propVal.length();
                    } else {
                        LogHelper.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "] ");
                    }
                } catch (Exception ex) {
                    LogHelper.info("Could not resolve placeholder '" + placeholder + "' in [" + text + "]: " + ex);
                }
                startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
            } else {
                startIndex = -1;
            }
        }
        return buf.toString();
    }

    /**
     *list的每一项（带${}）去跟parameter的key值比较，相等则把parameter的value替换list的值
     */
    public static ArrayList<String> resolveList(ArrayList<String> list, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || list == null || list.isEmpty()) {
            return list;
        }
        ArrayList<String> retureList = new ArrayList<>();
        list.forEach(str -> {
            if (str.contains(PLACEHOLDER_PREFIX)) {
                retureList.add(resolveString(str, parameter));
            } else {
                retureList.add(str);
            }
        });
        return retureList;
    }

    /**
     *用map的value值（带${}那种）去跟parameter的key比较，如相等则把value值赋给map的value
     */
    public static HashMap<String, String> resolveMap(HashMap<String, String> map, Map<String, String> parameter) {
        if (parameter == null || parameter.isEmpty() || map == null || map.isEmpty()) {
            return map;
        }
        HashMap<String, String> retureMap = new HashMap<>();
        map.forEach((key, value) -> {
            if (value.contains(PLACEHOLDER_PREFIX)) {
                retureMap.put(key, resolveString(value, parameter));
            }
        });
        return retureMap;
    }
}
