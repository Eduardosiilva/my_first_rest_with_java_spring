package br.com.esotk.my_first_rest_with_java_spring.config;

public interface TestConfigs {
    int SERVER_PORT = 8888;

    String HEADER_PARM_AUTHORIZATION = "Authorization";
    String HEADER_PARM_ORIGIN = "Origin";

    String ORIGIN_LOCALHOST = "http://localhost:8080";
    String ORIGIN_LOCALHOST_NOT_AUT = "http://localhost:8081";

}
