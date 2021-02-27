package ru.shift.certbotcft.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlPath {

    public static final String API_VERSION = "v1";
    public static final String API_PATH_PREFIX = "/api/" + API_VERSION;
    public static final String USER = "/user";
    public static final String USER_ID = "/{userId}";
    public static final String LICENSE = "/license";
    public static final String LICENSE_ID = "/{license_id}";


}

