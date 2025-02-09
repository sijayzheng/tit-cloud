package cn.sijay.gen.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <strong>GenProperties</strong>
 * <p>
 * GenProperties
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@Component
@ConfigurationProperties(prefix = "gen")
public class GenProperties {
    /**
     * 代码生成路径
     */
    private String path;
    /**
     * 代码生成作者
     */
    private String author;
    /**
     * 模板类型
     */
    private String templateType;
    /**
     * 包路径
     */
    private String packageName;
    /**
     * 生成方式
     */
    private String genType;
}
