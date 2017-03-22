package com.example.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by YG-MAC on 2017. 3. 11..
 */
public class IndexUtils {
    public static boolean verifyIndex(String index) {
        return !StringUtils.isEmpty(index) || StringUtils.isNumeric(index);
    }
}
