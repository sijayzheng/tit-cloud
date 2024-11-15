package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import cn.sijay.common.core.constant.Constants;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>SuperClassEnum</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/25 9:53
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum SuperClassType {
    NONE("", "", "无"),
    BASE_ENTITY("BaseEntity", "cn.sijay.common.web.base", "BaseEntity"),
    BASE_ENTITY_WITH_VERSION("BaseEntityWithVersion", "cn.sijay.common.web.base", "BaseEntityWithVersion"),
    ;
    @EnumValue
    private final String className;
    private final String packageName;
    @JsonValue
    private final String desc;

    public String getFullName() {
        return this.getPackageName() + Constants.DOT + this.getClassName();
    }
}
