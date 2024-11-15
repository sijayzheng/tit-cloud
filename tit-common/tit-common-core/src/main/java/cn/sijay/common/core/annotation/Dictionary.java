package cn.sijay.common.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <strong>DictDataType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-12
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dictionary {
}
