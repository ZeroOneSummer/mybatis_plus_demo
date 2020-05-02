//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.common;

import com.google.common.collect.Lists;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final int MAX_BYTE_VARCHAR = 4000;
    public static final int MAX_CHINESE_CHAR_WITH_VARCHAR = 1333;

    public StringUtils() {
    }

    public static boolean hasLength(CharSequence str) {
        return str != null && str.length() > 0;
    }

    public static boolean nullOrEq(String src, String des) {
        if (src == des) {
            return true;
        } else {
            return src != null ? src.equals(des) : false;
        }
    }

    public static void trimStrFields(Object target) {
        if (target != null) {
            List<Field> strFieldList = Lists.newLinkedList();

            for(Class superClass = target.getClass(); superClass != null; superClass = superClass.getSuperclass()) {
                Field[] fields = superClass.getDeclaredFields();
                List<Field> subStrFieldList = (List)Stream.of(fields).filter((fx) -> {
                    return String.class.equals(fx.getType());
                }).collect(Collectors.toList());
                if (subStrFieldList != null) {
                    strFieldList.addAll(subStrFieldList);
                }
            }

            try {
                Iterator var8 = strFieldList.iterator();

                while(var8.hasNext()) {
                    Field f = (Field)var8.next();
                    f.setAccessible(true);
                    String strValue = (String)f.get(target);
                    if (strValue != null) {
                        String trimValue = strValue.trim();
                        if (trimValue.length() != strValue.length()) {
                            f.set(target, strValue.trim());
                        }
                    }
                }
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            }

        }
    }

    public static String toUnderscore(String camelCaseName) {
        if (camelCaseName != null && camelCaseName.length() > 0) {
            StringBuilder result = new StringBuilder(camelCaseName.length() + 5);
            result.append(camelCaseName.substring(0, 1).toUpperCase());

            for(int i = 1; i < camelCaseName.length(); ++i) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append('_').append(Character.toUpperCase(ch));
                } else {
                    result.append(Character.toUpperCase(ch));
                }
            }

            return result.toString();
        } else {
            return camelCaseName;
        }
    }

    public static String toCamelCase(String underscoreName) {
        if (underscoreName != null && underscoreName.length() > 0) {
            StringBuilder result = new StringBuilder(underscoreName.length());
            boolean isUnderline = false;

            for(int i = 0; i < underscoreName.length(); ++i) {
                char ch = underscoreName.charAt(i);
                if ('_' == ch) {
                    isUnderline = true;
                } else if (isUnderline) {
                    result.append(Character.toUpperCase(ch));
                    isUnderline = false;
                } else {
                    result.append(Character.toLowerCase(ch));
                }
            }

            return result.toString();
        } else {
            return underscoreName;
        }
    }

    public static boolean hasText(CharSequence str) {
        if (str == null) {
            return false;
        } else {
            int strLen = str.length();

            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }

            return false;
        }
    }

    public static String trimIfOverlimit(String src, int maxBytes) {
        if (isEmpty(src)) {
            return src;
        } else if (src.length() <= maxBytes / 3) {
            return src;
        } else {
            byte[] bytes = src.getBytes(StringUtils.DefaultCharset.UTF8);
            if (bytes.length <= maxBytes) {
                return src;
            } else {
                String des = new String(bytes, 0, Math.min(bytes.length, maxBytes), StringUtils.DefaultCharset.UTF8);
                return des.length() > 1 ? des.substring(0, des.length() - 1) : des;
            }
        }
    }

    public static String trimIfOverChar(String src, int maxChar) {
        if (isEmpty(src)) {
            return src;
        } else {
            return src.length() <= maxChar ? src : src.substring(0, maxChar);
        }
    }

    static class DefaultCharset {
        public static final Charset UTF8 = Charset.forName("UTF-8");

        DefaultCharset() {
        }
    }

    public static String upperToUnderScore(String word) {
        return word.replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
    }

    public static String joinString(String[] list, String splitor) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.length; ++i) {
            sb.append(list[i]);
            if (i < list.length - 1) {
                sb.append(splitor);
            }
        }

        return sb.toString();
    }
}
