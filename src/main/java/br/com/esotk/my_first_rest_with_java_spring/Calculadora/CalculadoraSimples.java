package br.com.esotk.my_first_rest_with_java_spring.Calculadora;

import br.com.esotk.my_first_rest_with_java_spring.exception.ExceptionCalculadora;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class CalculadoraSimples {


    public double somar( Double numeroUm, Double numeroDois) {
        return numeroUm + numeroDois;
    }

    public double subtrair( Double numeroUm, Double numeroDois) {
        return numeroUm - numeroDois;
    }

    public double multiplicar( Double numeroUm, Double numeroDois) {
        return numeroUm * numeroDois;
    }

    public double dividir( Double numeroUm, Double numeroDois) {
        return numeroUm / numeroDois;
    }

    public double media( Double numeroUm, Double numeroDois) {
        return (numeroUm + numeroDois) / 2;
    }

    public double raiz( Double numero) {
        return Math.sqrt(numero);
    }

}
