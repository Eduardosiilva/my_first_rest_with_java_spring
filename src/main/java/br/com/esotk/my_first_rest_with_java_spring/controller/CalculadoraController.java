package br.com.esotk.my_first_rest_with_java_spring.controller;

import br.com.esotk.my_first_rest_with_java_spring.exception.ExceptionCalculadora;
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
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return convertToDouble(a) + convertToDouble(b);
    }

    // Endpoint para subtrair dois números
    // Exemplo de uso: /calculadora/subtrair/5/3
    @RequestMapping("/subtrair/{varUm}/{varDois}")
    public double subtrair(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b
    )throws Exception{

        if(!isNumeric(a) || !isNumeric(b)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return convertToDouble(a) + convertToDouble(b);
    }

    // Endpoint para multiplicar dois números
    // Exemplo de uso: /calculadora/multiplicar/5/3
    @RequestMapping("/multiplicar/{varUm}/{varDois}")
    public double multiplicar(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b
    )throws Exception{

        if(!isNumeric(a) || !isNumeric(b)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return convertToDouble(a) + convertToDouble(b);
    }

    // Endpoint para dividir dois números
    // Exemplo de uso: /calculadora/dividir/6/3
    @RequestMapping("/dividir/{varUm}/{varDois}")
    public double dividir(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b
    )throws Exception{
        if(!isNumeric(a) || !isNumeric(b)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        } else if ((convertToDouble(a) == 0 || convertToDouble(b) == 0)) {
            throw new ExceptionCalculadora("Divisão por zero não é permitida.");
        }
        return convertToDouble(a) + convertToDouble(b);
    }

    // Endpoint para calcular a média de dois números
    // Exemplo de uso: /calculadora/media/5/3
    @RequestMapping("/media/{varUm}/{varDois}")
    public double media(
            @PathVariable("varUm") String a,
            @PathVariable("varDois") String b
    )throws Exception{
        if(!isNumeric(a) || !isNumeric(b)){
            throw new ExceptionCalculadora("Um ou ambos os valores são inválidos, por favor, set apenas numeros");
        }
        return (convertToDouble(a) + convertToDouble(b)) / 2;
    }

    // Endpoint para calcular a raiz quadrada de um número
    // Exemplo de uso: /calculadora/raiz/9
    @RequestMapping("/raiz/{varUm}")
    public double raiz(
            @PathVariable("varUm") String a
    )throws Exception{
        if(!isNumeric(a)){
            throw new ExceptionCalculadora("O valor é inválido, por favor, set apenas numeros");
        } else if (convertToDouble(a) < 0) {
            throw new ExceptionCalculadora("Raiz quadrada de número negativo não é permitida.");
        }
        return Math.sqrt(convertToDouble(a));
    }
    
    private boolean isNumeric(String number) {
        if (number == null || number.isEmpty()) return false;
        String numberConverted = number.replace(",", ".");
        return numberConverted.matches("[+-]?[0-9]*\\.?[0-9]+");
        }

    private double convertToDouble(String number) {
        String numberConverted = number.replace(",", ".");
        return Double.parseDouble(numberConverted);
    }
}
