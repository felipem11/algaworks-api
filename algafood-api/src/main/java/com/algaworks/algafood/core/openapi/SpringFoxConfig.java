package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Server;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * 18.5. Selecionando os endpoints da API para gerar a documentação<br>
 * 18.6. Descrevendo informações da API na documentação<br>
 *
 * @author Felipe Martins
 * @version 1.0
 * @since 2020-04-15
 */

@Configuration
@EnableOpenApi
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
        Server testServer = new Server("test", "https://example.org", "for testing", Collections.emptyList(), Collections.emptyList());
        return new Docket(DocumentationType.OAS_30)
                .servers(serverLocal, testServer)
                .apiInfo(apiInfo())
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                    .paths(PathSelectors.any())
                    .build()
                .tags(
                        new Tag("Cidades","Gerencias as cidades"),
                        new Tag("Blog posts","Create, modify, delete and list blog posts")

                );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Felipe's AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1.0.0")
                .contact(new Contact("Felipe", "https://www.linkedin.com/in/felipe-martins01", "felipem11@outlook.com"))
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}