package cn.sijay.common.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <strong>ConfigProperties</strong>
 * <p>
 * ConfigProperties
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Data
@Component
@ConfigurationProperties(prefix = "configs")
public class ConfigProperties {
    private Integer passwordMaxRetryCount;
    private Integer passwordLockTime;
    private String fileStorage;
    private String tempFolder;
    private Integer tokenTimeout;
}
