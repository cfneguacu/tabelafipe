package br.com.springboot.tabelafipe.utils;

public class VeiculosUtils {

    public void validacaoDigitosRenavam(String renavam) {
        if (renavam.length() != 11)
            throw new IllegalArgumentException("RENAVAM inválido: precisa ter 11 digitos!");
    }
}
