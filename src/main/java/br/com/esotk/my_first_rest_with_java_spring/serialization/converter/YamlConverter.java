package br.com.esotk.my_first_rest_with_java_spring.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

public final class YamlConverter extends AbstractJackson2HttpMessageConverter {
    public YamlConverter() {
        super(new YAMLMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL),
                MediaType.parseMediaType("application/yaml"));
    }
}
