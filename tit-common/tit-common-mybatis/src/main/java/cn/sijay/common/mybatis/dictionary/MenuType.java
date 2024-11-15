package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>MenuType</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/2/1 11:41
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum MenuType {
    DIRECTORY("D", "目录"),
    MENU("M", "菜单"),
    BUTTON("B", "按钮"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
}
