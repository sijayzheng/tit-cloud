package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>TemplateType 前端页面模板类型</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 17:05
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum TemplateType {
    LIST("L", "列表"),
    TREE("T", "树表"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
}
