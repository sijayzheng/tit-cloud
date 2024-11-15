package cn.sijay.common.core.enums;

import cn.sijay.common.core.utils.FileUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <strong>GenFileType 生成文件路径</strong>
 * <p>
 *
 * </p>
 *
 * @author Sijay
 * @since 2024/1/12 15:56
 */
@Getter
@AllArgsConstructor
public enum GenFileType {
    JAVA("java", FileUtil.concatPath("src", "main", "java")),
    XML("xml", FileUtil.concatPath("src", "main", "resources")),
    VUE("vue", FileUtil.concatPath("src", "views")),
    JS("js", FileUtil.concatPath("src", "api")),
    SQL("sql", FileUtil.concatPath("sql")),
    ;
    private final String type;
    private final String genPath;
}
