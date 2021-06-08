package com.zdk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zdk
 * @date 2021/4/25 19:46
 * 日期转换工具类
 */
public class DateConversion {
    public static String getNowDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }
}
