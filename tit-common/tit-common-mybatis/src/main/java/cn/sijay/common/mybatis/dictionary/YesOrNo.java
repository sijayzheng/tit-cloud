package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>YesOrNo</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/8 17:35
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum YesOrNo {
    Y("Y", "是"),
    N("N", "否"),
    ;

    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

}

