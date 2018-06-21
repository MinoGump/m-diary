package com.mino.mdiary.util;

import com.mino.mdiary.enums.LogType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    public static void logEvent(Class clazz, LogType logType, String event) {
        log(clazz, logType, event);
    }

    public static void logEvent(Class clazz, LogType logType, String event, Object...args) {
        log(clazz, logType, String.format(event, args));
    }

    private static void log(Class clazz, LogType logType, String event) {
        Logger logger = LoggerFactory.getLogger(clazz);
        switch (logType) {
            case INFO:
                logger.info(event);
                break;
            case DEBUG:
                logger.debug(event);
                break;
            case WARN:
                logger.warn(event);
                break;
            case ERROR:
                logger.error(event);
                break;
            default:
                logger.info(event);
                break;
        }
    }
}
