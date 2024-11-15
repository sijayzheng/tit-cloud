package cn.sijay.system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <strong>TitSystemApplication</strong>
 * <p>
 * TitSystemApplication
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@OpenAPIDefinition(info = @Info(title = "山雀", version = "${springdoc.version}", description = "系统管理模块",
        contact = @Contact(name = "Sijay")))
@SpringBootApplication
public class TitSystem {
    public static void main(String[] args) {
        SpringApplication.run(TitSystem.class, args);
        System.out.println("===>  系统模块 启动成功  <===");
    }
}
