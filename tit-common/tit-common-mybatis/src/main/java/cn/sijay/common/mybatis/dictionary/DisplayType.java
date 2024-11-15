package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>DisplayType</strong>
 * <p>
 *
 * </p>
 *
 * @author sijay
 * @since 2024-04-28
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum DisplayType {
    DEFAULT("default", "默认"),
    PRIMARY("primary", "主要"),
    SUCCESS("success", "成功"),
    INFO("info", "信息"),
    WARNING("warning", "警告"),
    DANGER("danger", "危险"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
}
