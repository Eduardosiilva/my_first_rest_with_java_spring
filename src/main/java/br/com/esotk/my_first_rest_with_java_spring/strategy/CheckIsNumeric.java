package br.com.esotk.my_first_rest_with_java_spring.strategy;

import org.springframework.stereotype.Component;

@Component
public class CheckIsNumeric {

    public boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) return false;
        String numberConverted = number.replace(",", ".");
        return numberConverted.matches("[+-]?[0-9]*\\.?[0-9]+");
    }
}
