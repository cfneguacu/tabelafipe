package br.com.springboot.tabelafipe.unittests;

import br.com.springboot.tabelafipe.model.Modelo;
import br.com.springboot.tabelafipe.model.Veiculos;
import br.com.springboot.tabelafipe.utils.VeiculosUtils;
import org.junit.Assert;
import org.junit.Test;

public class VeiculoTest {

    @Test
    public void builderDeveCriarVeiculoComSucesso(){
        Veiculos veiculo = Veiculos.builder()
                .modelo_id(Modelo.builder()
                        .nome("Tiggo 2.0 16V Aut. 5p")
                        .build())
                .ano("2013-1")
                .renavam("63843842707")
                .placa("IAL-0989")
                .cor("Amarelo")
                .build();

        Assert.assertEquals("Tiggo 2.0 16V Aut. 5p", veiculo.getModelo_id().getNome());
        Assert.assertEquals("2013-1", veiculo.getAno());
        Assert.assertEquals("63843842707", veiculo.getRenavam());
        Assert.assertEquals("IAL-0989", veiculo.getPlaca());
        Assert.assertEquals("Amarelo", veiculo.getCor());
    }

    @Test
    public void builderNaoDeveCriarVeiculoComSucesso(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            Veiculos veiculo = Veiculos.builder()
                    .modelo_id(Modelo.builder()
                            .nome("Tiggo 2.0 16V Aut. 5p")
                            .build())
                    .ano("2013-1")
                    .renavam("63")
                    .placa("IAL-0989")
                    .cor("Amarelo")
                    .build();
        VeiculosUtils veiculosUtils = new VeiculosUtils();
        veiculosUtils.validacaoDigitosRenavam(veiculo.getRenavam());
    });
    }

    @Test
    public void deveRetornarRenavamInvalido(){
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            Veiculos veiculo = Veiculos.builder()
                    .renavam("123")
                    .build();
            VeiculosUtils veiculosUtils = new VeiculosUtils();
            veiculosUtils.validacaoDigitosRenavam(veiculo.getRenavam());
        });
    }

    @Test
    public void naoDeveRetornarRenavamInvalido(){
        Veiculos veiculo = Veiculos.builder()
                .renavam("12345678900")
                .build();
        VeiculosUtils veiculosUtils = new VeiculosUtils();
        veiculosUtils.validacaoDigitosRenavam(veiculo.getRenavam());
        Assert.assertEquals("12345678900", veiculo.getRenavam());
    }
}
