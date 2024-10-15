package br.com.springboot.tabelafipe.unittests;

import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.entity.YearEntity;
import br.com.springboot.tabelafipe.entity.ModelEntity;
import br.com.springboot.tabelafipe.utils.VehicleUtils;
import org.junit.Assert;
import org.junit.Test;

public class VehicleEntityTest {

    @Test
    public void builderDeveCriarVeiculoComSucesso(){
        VehicleEntity veiculo = VehicleEntity.builder()
                .modelEntityId(ModelEntity.builder()
                        .name("Tiggo 2.0 16V Aut. 5p")
                        .build())
                .yearEntityId(YearEntity.builder()
                        .code("2013-1")
                        .build())
                .renavam("63843842707")
                .licensePlate("IAL-0989")
                .color("Amarelo")
                .build();

        Assert.assertEquals("Tiggo 2.0 16V Aut. 5p", veiculo.getModelEntityId().getName());
        Assert.assertEquals("2013-1", veiculo.getYearEntityId().getCode());
        Assert.assertEquals("63843842707", veiculo.getRenavam());
        Assert.assertEquals("IAL-0989", veiculo.getLicensePlate());
        Assert.assertEquals("Amarelo", veiculo.getColor());
    }

    @Test
    public void builderNaoDeveCriarVeiculoComSucesso(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            VehicleEntity veiculo = VehicleEntity.builder()
                    .modelEntityId(ModelEntity.builder()
                            .name("Tiggo 2.0 16V Aut. 5p")
                            .build())
                    .yearEntityId(YearEntity.builder()
                            .code("2013-1")
                            .build())
                    .renavam("63")
                    .licensePlate("IAL-0989")
                    .color("Amarelo")
                    .build();
        VehicleUtils vehicleUtils = new VehicleUtils();
        vehicleUtils.digitValidationRenavam(veiculo.getRenavam());
    });
    }

    @Test
    public void deveRetornarRenavamInvalido(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            VehicleEntity veiculo = VehicleEntity.builder()
                    .renavam("123")
                    .build();
            VehicleUtils vehicleUtils = new VehicleUtils();
            vehicleUtils.digitValidationRenavam(veiculo.getRenavam());
        });
    }

    @Test
    public void naoDeveRetornarRenavamInvalido(){
        VehicleEntity vehicleEntity = VehicleEntity.builder()
                .renavam("12345678900")
                .build();
        VehicleUtils vehicleUtils = new VehicleUtils();
        vehicleUtils.digitValidationRenavam(vehicleEntity.getRenavam());
        Assert.assertEquals("12345678900", vehicleEntity.getRenavam());
    }
}
