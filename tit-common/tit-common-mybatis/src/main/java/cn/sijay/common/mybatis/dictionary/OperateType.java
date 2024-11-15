package cn.sijay.common.mybatis.dictionary;

import cn.sijay.common.core.annotation.Dictionary;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>BusinessType 操作类型</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/10 17:34
 */
@Dictionary
@Getter
@AllArgsConstructor
public enum OperateType {
    OTHER("OTHER", "其它"),
    CREATE("CREATE", "新增"),
    BATCH_CREATE("BATCH_CREATE", "批量新增"),
    MODIFY("MODIFY", "修改"),
    REMOVE("REMOVE", "删除"),
    BATCH_REMOVE("BATCH_REMOVE", "批量删除"),
    AUTHORIZE("AUTHORIZE", "授权"),
    EXPORT("EXPORT", "导出"),
    IMPORT("IMPORT", "导入"),
    FORCE_OUT("FORCE_OUT", "强退"),
    GENERATE_CODE("GENERATE_CODE", "生成代码"),
    DROP("DROP", "清空数据"),
    SELECT("SELECT", "查询"),
    DOWNLOAD("DOWNLOAD", "下载"),
    LOGIN("LOGIN", "登录"),
    LOGOUT("LOGOUT", "登出"),
    ;
    @EnumValue
    private final String code;
    @JsonValue
    private final String desc;
}