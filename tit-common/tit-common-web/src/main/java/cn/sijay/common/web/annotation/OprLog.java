package cn.sijay.common.web.annotation;

import cn.sijay.common.mybatis.dictionary.OperateType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>Log</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/22 16:13
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OprLog {
    String value() default "";

    OperateType operateType() default OperateType.OTHER;
}
