package cn.sijay.system.utils;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * <strong>DataDictUtil</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-26
 */
public class DictUtil {
    public static void getDictClass() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metaReader = new CachingMetadataReaderFactory();
        try {
            Resource[] resources = resolver.getResources("classpath*:cn/sijay/common/mybatis/dictionary/**/*.class");
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            for (Resource resource : resources) {
                MetadataReader reader = metaReader.getMetadataReader(resource);
                String className = reader.getClassMetadata().getClassName();
                Class<?> clazz = loader.loadClass(className);
                if (clazz.isAnnotationPresent(Dictionary.class)) {
                    for (Field field : clazz.getDeclaredFields()) {
                        System.out.println(clazz.getName());
                        if (field.isAnnotationPresent(EnumValue.class)) {
                            System.out.println(field.getName());
                        } else if (field.isAnnotationPresent(JsonValue.class)) {
                            System.out.println(field.getName());
                        }
                    }
                }
                System.out.println("======================");
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
