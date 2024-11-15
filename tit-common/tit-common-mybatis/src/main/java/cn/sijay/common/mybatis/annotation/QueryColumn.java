package cn.sijay.common.mybatis.annotation;

import cn.sijay.common.mybatis.dictionary.QueryType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>QueryColumn</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/9 15:31
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryColumn {
    QueryType value() default QueryType.EQUAL;

}
