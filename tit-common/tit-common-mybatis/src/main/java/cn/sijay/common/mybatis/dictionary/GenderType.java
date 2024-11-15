package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenderType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-16
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum GenderType {
    MALE("M", "男"),
    FEMALE("F", "女"),
    UNKNOWN("U", "未知"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

}
