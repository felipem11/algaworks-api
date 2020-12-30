package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.core.ObjectMapper.JacksonOffsetDateTimeMapper;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.Example;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * 18.5. Selecionando os endpoints da API para gerar a documentação<br>
 * 18.6. Descrevendo informações da API na documentação<br>
 * 18.13. Desafio: descrevendo códigos de status de respostas de forma global<br>
 * 18.15. Referenciando modelo de representação de problema com códigos de status de erro<br>
 * 18.15. Referenciando modelo de representação de problema com códigos de status de erro<br>
 *
 * @author Felipe Martins
 * @version 1.0
 * @since 2020-04-15
 */

@Configuration
@EnableOpenApi
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Autowired
    private JacksonOffsetDateTimeMapper jacksonOffsetDateTimeMapper;


    @Bean
    public Docket apiDocket() throws JsonProcessingException {
        var typeResolver = new TypeResolver();

        Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
        Server testServer = new Server("test", "https://example.org", "for testing", Collections.emptyList(), Collections.emptyList());
        Docket docket = new Docket(DocumentationType.OAS_30)
                .servers(serverLocal, testServer)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag("Cidades", "Gerencias as cidades"),
                        new Tag("Blog posts", "Create, modify, delete and list blog posts")

                )
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponseMessages())
                .globalResponses(HttpMethod.POST, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages())

                .additionalModels(typeResolver.resolve(Problem.class));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Felipe's AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1.0.0")
                .contact(new Contact("Felipe", "https://www.linkedin.com/in/felipe-martins01", "felipem11@outlook.com"))
                .build();
    }

    private List<Response> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code("500")
                        .description("Erro interno do servidor")
                        .build(),
                new ResponseBuilder()
                        .code("406")
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build()
        );
    }

    private List<Response> globalPostPutResponseMessages() throws JsonProcessingException {


        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .examples(Arrays.asList(getExample(BAD_REQUEST.value())))
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .examples(Arrays.asList(getExample(INTERNAL_SERVER_ERROR.value())))
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .examples(Arrays.asList(getExample(UNSUPPORTED_MEDIA_TYPE.value())))
                        .build()
        );
    }

    private List<Response> globalDeleteResponseMessages() throws JsonProcessingException {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .examples(Arrays.asList(getExample(BAD_REQUEST.value())))
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .examples(Arrays.asList(getExample(INTERNAL_SERVER_ERROR.value())))
                        .build()
        );
    }

    private Example getExample(int status) throws JsonProcessingException {
        Problem.Object campos = Problem.Object.builder()
                .name("preco")
                .userMessage("O preco e obrigatorio").build();
        Problem build = Problem.builder()
                .detail("detalhe")
                .status(status)
                .timestamp(OffsetDateTime.now())
                .title("titulo")
                .userMessage("user message")
                .objects(Arrays.asList(campos))
                .build();


        String s = jacksonOffsetDateTimeMapper.objectMapper().writeValueAsString(build);

        Example example = new Example("aaa", "bbb", "ccc", s, "ddd", "eee");
        return example;
    }




    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}