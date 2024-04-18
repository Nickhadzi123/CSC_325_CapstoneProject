package com.capstoneproject;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

    private static final Logger LOGGER = Logger.getLogger(LoggerUtil.class.getName());

    public static void logError(Class<?> clazz, Exception e) {
        LOGGER.log(Level.SEVERE, "An error occurred in " + clazz.getSimpleName() + ". Please contact support for assistance.", e);
    }
}
