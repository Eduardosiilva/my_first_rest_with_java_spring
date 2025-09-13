package br.com.esotk.my_first_rest_with_java_spring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    // Endpoint para somar dois números
    // Exemplo de uso: /calculadora/somar/5/3
    @RequestMapping("/somar/{varUm}/{varDois}")
    public double somar(
        @PathVariable("varUm") String a,
        @PathVariable("varDois") String b
    ) throws Exception{

        if(!isNumeric(a) || !isNumeric(b)){
            throw new IllegalArgumentException("Por favor, envie apenas números!");
        }
        return convertToDouble(a) + convertToDouble(b);
    }

    // Endpoint para subtrair dois números
    // Exemplo de uso: /calculadora/subtrair/5/3
    @RequestMapping("/subtrair/{varUm}/{varDois}")
    public double subtrair(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b) {
        return 1D;
    }

    // Endpoint para multiplicar dois números
    // Exemplo de uso: /calculadora/multiplicar/5/3
    @RequestMapping("/multiplicar/{varUm}/{varDois}")
    public double multiplicar(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b) {
        return 1D;
    }

    private boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) return false;
        String numberConverted = number.replace(",", ".");
        return numberConverted.matches("[+-]?[0-9]*\\.?[0-9]+");
        }

    private double convertToDouble(String number) {
        if (number == null || number.isEmpty()) throw new IllegalArgumentException();
        String numberConverted = number.replace(",", ".");
        return Double.parseDouble(numberConverted);
    }
}
