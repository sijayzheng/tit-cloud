package cn.sijay.common.core.utils;

import cn.sijay.common.core.exception.UtilException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <strong>BeanUtil</strong>
 * <p>
 * BeanUtil
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanUtil {
    public static <S, T> void copyProperties(Class<S> sourceClass, Class<T> targetClass) {
        copyProperties(sourceClass, targetClass, true, false);
    }

    public static <S, T> void copyProperties(S source, T target) {
        copyProperties(source.getClass(), target.getClass(), true, false);
    }

    public static <S, T> T copyProperties(S source, Class<T> targetClass) {
        T target;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new UtilException("调用{}的无参构造方法失败", targetClass);
        }
        copyProperties(source.getClass(), targetClass, true, false);
        return target;
    }

    public static <S, T> void copyProperties(Class<S> sourceClass, Class<T> targetClass, boolean ignoreEmpty, boolean includeSupperClassFields) {
        Map<String, Field> sourceFieldMap = StreamUtil.toMap(getFields(sourceClass, includeSupperClassFields), Field::getName, field -> field);
        Map<String, Field> targetFieldMap = StreamUtil.toMap(getFields(targetClass, includeSupperClassFields), Field::getName, field -> field);
        sourceFieldMap.forEach((key, value) -> {
            if (targetFieldMap.containsKey(key)) {
                Field sourceField = sourceFieldMap.get(key);
                Field targetField = targetFieldMap.get(key);
                sourceField.setAccessible(true);
                try {
                    if (ignoreEmpty) {
                        if (ObjectUtils.isNotEmpty(sourceField.get(sourceClass))) {
                            targetField.setAccessible(true);
                            targetField.set(targetClass, sourceField.get(sourceClass));
                        }
                    } else {
                        targetField.setAccessible(true);
                        targetField.set(targetClass, sourceField.get(sourceClass));
                    }
                } catch (IllegalAccessException e) {
                    throw new UtilException("");
                }
            }

        });
        System.out.println("--------------------------------------------");
        targetFieldMap.forEach((key, value) -> {
            System.out.println(key);
        });
    }

    public static <T> List<Field> getFields(Class<T> clazz) {
        return getFields(clazz, false);
    }

    public static <T> List<Field> getFields(String clazzName) throws ClassNotFoundException {
        return getFields(Class.forName(clazzName), false);
    }

    public static <T> List<Field> getFields(String clazzName, boolean includeSupperClassFields) throws ClassNotFoundException {
        return getFields(Class.forName(clazzName), includeSupperClassFields);
    }

    public static <T> List<Field> getFields(Class<T> clazz, boolean includeSupperClassFields) {
        List<Field> list = new ArrayList<>(Arrays.stream(clazz.getDeclaredFields()).filter(field -> !field.getName().equals("serialVersionUID"))
                                                 .toList());
        if (includeSupperClassFields && !Object.class.equals(clazz.getSuperclass())) {
            list.addAll(getFields(clazz.getSuperclass()));
        }
        return list;
    }

}
