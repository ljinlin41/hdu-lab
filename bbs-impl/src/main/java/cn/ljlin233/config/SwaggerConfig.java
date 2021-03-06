package cn.ljlin233.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lvjinlin42@foxmail.com
 * @date 2019/12/15 18:13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(this.apiInfo()).select().apis(
            RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("api文档")
            .description("项目描述，代码地址 https://github.com/ljinlin41/hdu-lab")
            .termsOfServiceUrl("https://github.com/ljinlin41/hdu-lab")
            .version("1.0")
            .build();
    }

}
