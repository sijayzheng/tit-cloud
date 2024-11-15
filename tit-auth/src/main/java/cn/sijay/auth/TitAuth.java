package cn.sijay.auth;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <strong>TitAuthApplication</strong>
 * <p>
 * TitAuthApplication
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@OpenAPIDefinition(info = @Info(title = "山雀", version = "${springdoc.version}", description = "认证中心模块",
        contact = @Contact(name = "Sijay")))
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients
public class TitAuth {
    public static void main(String[] args) {
        SpringApplication.run(TitAuth.class, args);
        System.out.println("===>  认证模块 启动成功  <===");
    }
}
