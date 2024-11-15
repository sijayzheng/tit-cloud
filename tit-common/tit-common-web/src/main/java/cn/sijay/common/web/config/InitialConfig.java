package cn.sijay.common.web.config;

import cn.sijay.common.core.utils.PrintUtil;
import cn.sijay.common.web.properties.ConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * <strong>InitialConfig</strong>
 * <p>
 * InitialConfig
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ConfigProperties.class)
public class InitialConfig {
    final private ConfigProperties configProperties;

    //初始化变量
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        PrintUtil.info("系统初始化");
    }
}
