package cn.sijay.gen;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <strong>TitGenApplication</strong>
 * <p>
 * TitGenApplication
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@OpenAPIDefinition(info = @Info(title = "山雀", version = "${springdoc.version}", description = "代码生成模块",
        contact = @Contact(name = "Sijay")))
@SpringBootApplication
public class TitGen {
    public static void main(String[] args) {
        SpringApplication.run(TitGen.class, args);
        System.out.println("===>  生成模块 启动成功  <===");
    }
}
