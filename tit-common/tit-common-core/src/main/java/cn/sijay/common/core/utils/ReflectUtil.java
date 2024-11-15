package cn.sijay.common.core.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * <strong>ReflectUtil</strong>
 * <p>
 * ReflectUtil
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectUtil {
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object newInstance(String className) {
        try {
            return Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // public static List<SelectOption<String>> enumToOption(String className) {
    //     List<SelectOption<String>> optionMap = new ArrayList<>();
    //     Method labelMethod = null;
    //     try {
    //         Class<?> clazz = Class.forName(JavaType.getByCode(className).getPkg());
    //         String labelField = "";
    //         for (Field field : clazz.getDeclaredFields()) {
    //             if (field.getAnnotation(JsonValue.class) != null) {
    //                 labelField = field.getName();
    //             }
    //         }
    //         for (Method method : clazz.getMethods()) {
    //             if (method.getName().startsWith("get")) {
    //                 if (method.getName().equalsIgnoreCase("get" + labelField)) {
    //                     labelMethod = method;
    //                 }
    //             }
    //         }
    //         if (labelMethod != null) {
    //             for (Object o : clazz.getEnumConstants()) {
    //                 String value = labelMethod.invoke(o).toString();
    //                 optionMap.add(new SelectOption<>(value, value));
    //             }
    //         }
    //     } catch (ClassNotFoundException e) {
    //         throw new BaseException(ExceptionEnum.REFLECT_CLASS_NOT_FOUND_ERROR, className);
    //     } catch (InvocationTargetException | IllegalAccessException e) {
    //         throw new BaseException(ExceptionEnum.REFLECT_METHOD_INVOKE_ERROR, className, labelMethod);
    //     }
    //     return optionMap;
    // }

}
