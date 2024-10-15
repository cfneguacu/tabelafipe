package br.com.springboot.tabelafipe.utils;

public class VehicleUtils {

    public void digitValidationRenavam(String renavam) {
        if (renavam.length() != 11)
            throw new IllegalArgumentException("RENAVAM inválido: precisa ter 11 digitos!");
    }
}
