package com.ushio.wechat.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelper {
    public static final Logger logger = LoggerFactory.getLogger(LogHelper.class);

    public static void info(String msg){
        logger.info(msg);
    }

}
