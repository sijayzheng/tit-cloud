package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>QueryType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/9 15:46
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum QueryType {
    LIKE("like", "包含"),
    EQUAL("eq", "等于"),
    GREATER_THAN("gt", "大于"),
    GREATER_OR_EQUAL("ge", "大于等于"),
    LESS_THAN("lt", "小于"),
    LESS_OR_EQUAL("le", "小于等于"),
    BETWEEN("between", "在范围内"),
    IN("in", "在列表内"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static QueryType getByCode(String code) {
        if (code == null) {
            return QueryType.EQUAL;
        }
        for (QueryType queryType : QueryType.values()) {
            if (queryType.getCode().equals(code)) {
                return queryType;
            }
        }
        return QueryType.EQUAL;
    }

}
