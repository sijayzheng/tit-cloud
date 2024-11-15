package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenType 生成方式</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 17:10
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum GenType {
    PROJECT("P", "项目中"),
    ZIP_FILE("Z", "压缩包"),
    CUSTOM("C", "自定义位置"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;

    public static GenType of(String code) {
        for (GenType genType : GenType.values()) {
            if (genType.getCode().equals(code)) {
                return genType;
            }
        }
        return ZIP_FILE;
    }
}
