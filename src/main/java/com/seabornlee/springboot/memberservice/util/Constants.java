package com.seabornlee.springboot.memberservice.util;

public class Constants {

    //默认页大小
    public static final int DEFAULT_PAGE_SIZE = 10;
    //session user name
    public static final String SESSION_USER_NAME_KEY = "SESSION_USER_NAME_KEY";

    private Constants() {

    }

    public static Integer ensurePositiveValue(Integer origin, int defaultValue) {
        return null == origin || origin < 1 ? defaultValue : origin;
    }
}
