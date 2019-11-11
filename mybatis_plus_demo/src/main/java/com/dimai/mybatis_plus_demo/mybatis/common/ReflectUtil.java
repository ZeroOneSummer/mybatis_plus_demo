//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.common;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReflectUtil {
    public ReflectUtil() {
    }

    public static Object getFieldValue(Object obj, String fieldName) throws IllegalArgumentException, IllegalAccessException {
        Object result = null;
        Field field = getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);

            try {
                result = field.get(obj);
            } catch (IllegalArgumentException var5) {
                throw var5;
            } catch (IllegalAccessException var6) {
                throw var6;
            }
        }

        return result;
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        Class clazz = obj.getClass();

        while(clazz != Object.class) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException var5) {
                clazz = clazz.getSuperclass();
            }
        }

        return field;
    }

    public static void setFieldValue(Object obj, String fieldName, String fieldValue) throws IllegalArgumentException, IllegalAccessException {
        Field field = getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException var5) {
                throw var5;
            } catch (IllegalAccessException var6) {
                throw var6;
            }
        }

    }

    public static List<Field> getAllFields(Class<?> type) {
        return getAllFields(new ArrayList(), type);
    }

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static List<String> findClassesByAnnotationOnPackage(Class<? extends Annotation> annotationType, String packageName) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(annotationType));
        List<String> classNames = new ArrayList();
        Iterator var4 = scanner.findCandidateComponents(packageName).iterator();

        while(var4.hasNext()) {
            BeanDefinition bd = (BeanDefinition)var4.next();
            classNames.add(bd.getBeanClassName());
        }

        return classNames;
    }
}
