package cn.sijay.gateway;

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springdoc.core.utils.Constants.DEFAULT_API_DOCS_URL;

/**
 * <strong>TitGatewayApplication</strong>
 * <p>
 * TitGatewayApplication
 * </p>
 *
 * @author Sijay
 * @since 2024-04-04
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TitGateway {

    public static void main(String[] args) {
        SpringApplication.run(TitGateway.class, args);
        System.out.println("===>  网关模块 启动成功  <===");
    }

    @Bean
    @Lazy(false)
    public Set<SwaggerUrl> apis(RouteDefinitionLocator locator, SwaggerUiConfigParameters swaggerUiConfigParameters) {
        Set<SwaggerUrl> urls = Objects.requireNonNull(locator.getRouteDefinitions().collectList().block()).stream()
                                      .filter(routeDefinition -> routeDefinition.getId().matches("tit-.+-service"))
                                      .map(routeDefinition -> new SwaggerUrl(routeDefinition.getId(), DEFAULT_API_DOCS_URL + "/" + routeDefinition.getId(), null))
                                      .collect(Collectors.toSet());
        swaggerUiConfigParameters.setUrls(urls);
        return urls;
    }
}
