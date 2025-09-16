package br.com.esotk.my_first_rest_with_java_spring.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.List;

public class ObjectMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(O origin, Class<D> destination) {

        return mapper.map(origin, destination);
    }

    public static<O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {

        return origin.stream()
                .map(element -> parseObject(element, destination))
                .toList();
    }
}
