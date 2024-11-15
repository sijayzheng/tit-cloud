package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>JavaType</strong>
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
public enum JavaType {
    BOOLEAN("Boolean", "布尔", ""),
    BYTE("Byte", "字节", ""),
    CHARACTER("Character", "字符", ""),
    DOUBLE("Double", "浮点", ""),
    INTEGER("Integer", "整数", ""),
    LONG("Long", "长整数", ""),
    OBJECT("Object", "对象", ""),
    STRING("String", "字符串", ""),

    BIG_DECIMAL("BigDecimal", "大数值", "java.math.BigDecimal"),

    DATE("LocalDate", "日期", "java.time.LocalDate"),
    DATE_TIME("LocalDateTime", "日期时间", "java.time.LocalDateTime"),
    TIME("LocalTime", "时间", "java.time.LocalTime"),

    LIST("List", "列表", "java.util.List"),
    MAP("Map", "映射", "java.util.Map"),
    SET("Set", "集", "java.util.Set"),
    LONG_LIST("List<Long>", "列表", "java.util.List"),

    MENU_TYPE("MenuType", "菜单类型", "cn.sijay.common.mybatis.dictionary.MenuType"),
    OPERATE_TYPE("OperateType", "操作类型", "cn.sijay.common.mybatis.dictionary.OperateType"),
    QUERY_TYPE("QueryType", "查询类型", "cn.sijay.common.mybatis.dictionary.QueryType"),
    GEN_TYPE("GenType", "生成方式", "cn.sijay.common.mybatis.dictionary.GenType"),
    INPUT_TYPE("InputType", "输入方式", "cn.sijay.common.mybatis.dictionary.InputType"),
    JAVA_TYPE("JavaType", "Java数据类型", "cn.sijay.common.mybatis.dictionary.JavaType"),
    SUPER_CLASS_TYPE("SuperClassType", "父类", "cn.sijay.common.mybatis.dictionary.SuperClassType"),
    TEMPLATE_TYPE("TemplateType", "前端类型", "cn.sijay.common.mybatis.dictionary.TemplateType"),
    DISPLAY_TYPE("DisplayType", "回显样式", "cn.sijay.common.mybatis.dictionary.DisplayType"),
    GENDER_TYPE("GenderType", "性别", "cn.sijay.common.mybatis.dictionary.GenderType"),
    YES_OR_NO("YesOrNo", "是否", "cn.sijay.common.mybatis.dictionary.YesOrNo"),
    ;
    @EnumValue
    private final String className;
    @JsonValue
    private final String desc;
    private final String fullName;

}
