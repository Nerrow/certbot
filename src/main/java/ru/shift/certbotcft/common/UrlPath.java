package ru.shift.certbotcft.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlPath {

    public static final String API_VERSION = "v1";
    public static final String API_PATH_PREFIX = "/api/" + API_VERSION;
    public static final String USER = API_PATH_PREFIX + "/user";
    public static final String USER_ID = USER + "/{userId}";
    public static final String LICENSE = API_PATH_PREFIX + "/license";
    public static final String LICENSE_ID = LICENSE + "/{license_id}";

}

