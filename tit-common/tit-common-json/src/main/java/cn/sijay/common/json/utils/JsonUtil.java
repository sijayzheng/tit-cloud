package cn.sijay.common.json.utils;

import cn.hutool.extra.spring.SpringUtil;
import cn.sijay.common.core.enums.ExceptionEnum;
import cn.sijay.common.core.exception.UtilException;
import cn.sijay.common.core.utils.StringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <strong>JsonUtil</strong>
 * <p>
 * JsonUtil
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtil {
    private static final ObjectMapper OBJECT_MAPPER = SpringUtil.getBean(ObjectMapper.class);

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    public static String toJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UtilException(ExceptionEnum.JSON_SERIAL_ERROR);
        }
    }

    public static String toPrettyJsonString(Object object) {
        if (Objects.isNull(object)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UtilException(ExceptionEnum.JSON_SERIAL_ERROR);
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (bytes == null || bytes.length == 0 || clazz == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.JSON_PARSE_ERROR);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        if (StringUtil.isBlank(text)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.JSON_PARSE_ERROR);
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (StringUtil.isEmpty(text)) {
            return new ArrayList<>();
        }
        try {
            return OBJECT_MAPPER.readValue(text, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            throw new UtilException(ExceptionEnum.JSON_PARSE_ERROR);
        }
    }
}
