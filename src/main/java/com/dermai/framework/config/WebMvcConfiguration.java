package com.dermai.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Knife4j config
 *
 * @author Shaobo
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
    /**
     * Generate Interface Documentation via knife4j
     * @return docket
     */
    @Bean
    public Docket docket() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Derm AI Interface Documentation")
                .version("1.0")
                .description("Derm AI Interface Documentation")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dermai.project"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * Setting static resource mapping
     * @param registry
     */
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public CorsFilter corsFilter()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // Setting the allowed request origin address 设置访问源地址
        config.addAllowedOriginPattern("*");
        // Setting the allowed request origin header设置访问源请求头
        config.addAllowedHeader("*");
        // Seeting the allowed request methods 设置访问源请求方法
        config.addAllowedMethod("*");
        // validation 1800s
        config.setMaxAge(1800L);
        // Add mapped paths to intercept all requests
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        // return new CorsFilter
        return new CorsFilter(source);
    }

}
