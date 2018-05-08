package com.cmiot.api.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 时间日期工具类
 *
 * @author zhangst
 * @version 2018-5-5
 */
public class DateUtils {

    /**
     * 格式化时间戳为 2018-05-04T00:06:49Z 格式字符串
     */
    public static String Instant2String(Instant instant) {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
        String strDate = instant.atZone(ZoneId.of("Asia/Shanghai")).format(dtf);
        return strDate;
    }

    /**
     * String转换为时间戳Instant
     */
    public static Instant String2Instant(String strDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;
        Instant instant = Instant.from(dtf.parse(strDate));
        return instant;
    }

}
