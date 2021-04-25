package com.zdk.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zdk
 * @date 2021/4/25 19:46
 */
public class DateConversion {
    public static String getNowDate() throws JsonProcessingException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
