package br.com.esotk.my_first_rest_with_java_spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class ConvertDouble {

    public double convertToDouble(String number) {
        String numberConverted = number.replace(",", ".");
        return Double.parseDouble(numberConverted);
    }
}
