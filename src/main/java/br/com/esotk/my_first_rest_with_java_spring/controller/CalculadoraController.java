package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.Calculadora.CalculadoraSimples;
import br.com.esotk.my_first_rest_with_java_spring.exception.ExceptionCalculadora;
import br.com.esotk.my_first_rest_with_java_spring.strategy.CheckIsNumeric;
import br.com.esotk.my_first_rest_with_java_spring.strategy.ConvertDouble;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    @Autowired
    CalculadoraSimples calculadoraSimples;

    @Autowired
    private CheckIsNumeric checkIsNumeric;

    @Autowired
    private ConvertDouble convertDouble;

    // Endpoint para somar dois números
    // Exemplo de uso: /calculadora/somar/5/3

    @RequestMapping("/somar/{varUm}/{varDois}")
    public double somar(
        @PathVariable("varUm") String numeroUm,
        @PathVariable("varDois") String numeroDois
    ) throws Exception{
        if(!checkIsNumeric.isNumeric(numeroUm) || !checkIsNumeric.isNumeric(numeroDois)){
        throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return calculadoraSimples.somar(convertDouble.convertToDouble(numeroUm), convertDouble.convertToDouble(numeroDois));
    }

    // Endpoint para subtrair dois números
    // Exemplo de uso: /calculadora/subtrair/5/3
    @RequestMapping("/subtrair/{varUm}/{varDois}")
    public double subtrair(
            @PathVariable("varUm") String numeroUm,
            @PathVariable("varDois") String numeroDois
    )throws Exception{

        if(!checkIsNumeric.isNumeric(numeroUm) || !checkIsNumeric.isNumeric(numeroDois)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return calculadoraSimples.subtrair(convertDouble.convertToDouble(numeroUm), convertDouble.convertToDouble(numeroDois));
    }

    // Endpoint para multiplicar dois números
    // Exemplo de uso: /calculadora/multiplicar/5/3
    @RequestMapping("/multiplicar/{varUm}/{varDois}")
    public double multiplicar(
            @PathVariable("varUm") String numeroUm,
            @PathVariable("varDois") String numeroDois
    )throws Exception{

        if(!checkIsNumeric.isNumeric(numeroUm) || !checkIsNumeric.isNumeric(numeroDois)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return calculadoraSimples.multiplicar(convertDouble.convertToDouble(numeroUm), convertDouble.convertToDouble(numeroDois));
    }

    // Endpoint para dividir dois números
    // Exemplo de uso: /calculadora/dividir/6/3
    @RequestMapping("/dividir/{varUm}/{varDois}")
    public double dividir(
            @PathVariable("varUm") String numeroUm,
            @PathVariable("varDois") String numeroDois
    )throws Exception{
        if(!checkIsNumeric.isNumeric(numeroUm) || !checkIsNumeric.isNumeric(numeroDois)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        } else if ((convertDouble.convertToDouble(numeroUm) == 0 || convertDouble.convertToDouble(numeroDois) == 0)) {
            throw new ExceptionCalculadora("Divisão por zero não é permitida.");
        }
        return calculadoraSimples.dividir(convertDouble.convertToDouble(numeroUm), convertDouble.convertToDouble(numeroDois));
    }

    // Endpoint para calcular a média de dois números
    // Exemplo de uso: /calculadora/media/5/3
    @RequestMapping("/media/{varUm}/{varDois}")
    public double media(
            @PathVariable("varUm") String numeroUm,
            @PathVariable("varDois") String numeroDois
    )throws Exception{
        if(!checkIsNumeric.isNumeric(numeroUm) || !checkIsNumeric.isNumeric(numeroDois)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return calculadoraSimples.media(convertDouble.convertToDouble(numeroUm), convertDouble.convertToDouble(numeroDois));
    }

    // Endpoint para calcular a raiz quadrada de um número
    // Exemplo de uso: /calculadora/raiz/9
    @RequestMapping("/raiz/{var}")
    public double raiz(
            @PathVariable("var") String numero
    )throws Exception{
        if(!checkIsNumeric.isNumeric(numero)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        } else if (convertDouble.convertToDouble(numero) < 0) {
            throw new ExceptionCalculadora("Raiz quadrada de número negativo não é permitida.");
        }
        return calculadoraSimples.raiz(convertDouble.convertToDouble(numero));
    }
}
