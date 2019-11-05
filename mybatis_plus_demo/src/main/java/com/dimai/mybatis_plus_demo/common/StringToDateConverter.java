package com.dimai.mybatis_plus_demo.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.dozer.DozerConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换解析
 */
public class StringToDateConverter extends DozerConverter<String, Date> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringToDateConverter.class);

    private static final String PATTERN_STR_DATE1 = "yyyyMMdd";
    private static final String PATTERN_STR_DATE2 = "yyyy-MM-dd";
    private static final String PATTERN_STR_DATE3 = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_STR_DATE4 = "yyyy/MM/dd";
    private static final String PATTERN_STR_DATE5 = "yyyy/MM/dd HH:mm:ss";

    public StringToDateConverter() {
        super(String.class, Date.class);
    }

    /**
     * String -> Date
     */
    @Override
    public Date convertTo(String source, Date destination) {
        String pattern = PATTERN_STR_DATE3; //默认值
        try {
            if (StringUtils.isNotBlank(source)){
                if(isRightDateStr(source, PATTERN_STR_DATE1)) {
                    pattern = PATTERN_STR_DATE1;
                }else if(isRightDateStr(source, PATTERN_STR_DATE2)){
                    pattern = PATTERN_STR_DATE2;
                }else if(isRightDateStr(source, PATTERN_STR_DATE3)){
                    pattern = PATTERN_STR_DATE3;
                }else if(isRightDateStr(source, PATTERN_STR_DATE4)){
                    pattern = PATTERN_STR_DATE4;
                }else if(isRightDateStr(source, PATTERN_STR_DATE5)){
                    pattern = PATTERN_STR_DATE5;
                }
            }
            return StringUtils.isBlank(source) ? null : DateUtils.parseDate(source, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Date -> String
     */
    @Override
    public String convertFrom(Date source, String destination) {
        FastDateFormat df = FastDateFormat.getInstance(PATTERN_STR_DATE3, null, null);
        return source == null ? null : df.format(source);
    }

    /**
     * 判断是否是对应的格式的日期字符串
     */
    public static boolean isRightDateStr(String dateStr, String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            dateFormat.setLenient(false);   //严格校验日期
            Date date = dateFormat.parse(dateStr);
            String newDateStr = dateFormat.format(date);
            if(dateStr.equals(newDateStr)){
                return true;
            }else {
                LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",dateStr,datePattern);
                return false;
            }
        } catch (ParseException e) {
            LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",dateStr,datePattern);
            return false;
        }
    }
}