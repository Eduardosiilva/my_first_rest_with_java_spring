package br.com.esotk.my_first_rest_with_java_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                  .ignoreAcceptHeader(false)
                  .useRegisteredExtensionsOnly(false)
                  .defaultContentType(org.springframework.http.MediaType.APPLICATION_JSON)
                  .mediaType("json", org.springframework.http.MediaType.APPLICATION_JSON)
                  .mediaType("xml", org.springframework.http.MediaType.APPLICATION_XML)
                  .mediaType("yaml", MediaType.APPLICATION_YAML);
    }
}
