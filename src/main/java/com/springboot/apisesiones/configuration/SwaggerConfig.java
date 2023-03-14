package com.springboot.apisesiones.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.apisesiones.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Order Service API",
                "Actualmente muchas aplicaciones, como los portales transaccionales, presentan vulnerabilidad de multiple sesiones generadas a través de diferentes dispositivos como laptos, celular, etc. Es por ello que se propone como solución la construcción de una API la cual se encargue de manejar las sesiones.\n" +
                        "\n" +
                        "En esta solución se propone exponer servicios que permiten la creación, validación y elimianción de una sesión a través de protocolos HTTP.",
                "1.0",
                "http://codmind.com/terms",
                new Contact("Edisson Chamorro", "https://www.linkedin.com/in/john-edisson-chamorro-coral-76ab74228/", "john.chamorroc@gmail.com"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}