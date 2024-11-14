package br.com.springboot.tabelafipe.utils;

public class VehicleUtils {

    public void digitValidationRenavam(String renavam) {
        if (renavam.length() != 11)
            throw new IllegalArgumentException("RENAVAM inválido: precisa ter 11 digitos!");
    }

    public static int getActiveRelayTemp(String licensePlate) {
        int activeRelayTemp;

        if(licensePlate.charAt(licensePlate.length()-1)=='0'|| licensePlate.charAt(licensePlate.length()-1)=='1')

        {
            activeRelayTemp = 1;
        }else if(licensePlate.charAt(licensePlate.length()-1)=='2'|| licensePlate.charAt(licensePlate.length()-1)=='3')

        {
            activeRelayTemp = 2;
        }else if(licensePlate.charAt(licensePlate.length()-1)=='4'|| licensePlate.charAt(licensePlate.length()-1)=='5')

        {
            activeRelayTemp = 3;
        }else if(licensePlate.charAt(licensePlate.length()-1)=='6'|| licensePlate.charAt(licensePlate.length()-1)=='7')

        {
            activeRelayTemp = 4;
        }else

        {
            activeRelayTemp = 5;
        }
        return activeRelayTemp;
    }
}
