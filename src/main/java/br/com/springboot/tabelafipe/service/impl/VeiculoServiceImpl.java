package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.model.Caracteristica;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.repository.VeiculosRepository;
import br.com.springboot.tabelafipe.service.TabelaFipeService;
import br.com.springboot.tabelafipe.service.VeiculoService;
import br.com.springboot.tabelafipe.status.Status;
import br.com.springboot.tabelafipe.utils.VeiculosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculosRepository veiculosRepository;

    @Autowired
    private TabelaFipeService tabelaFipeService;

    @Override
    public Iterable<Veiculo> buscarTodos() {
       return veiculosRepository.findAll();
    }

    @Override
    public Veiculo buscarPorId(Long id) {
        return veiculosRepository.findById(id).get();
    }

    @Override
    public void inserir(Veiculo veiculo) {
        salvarVeiculoComCaracteristica(veiculo);
    }

    @Override
    public void atualizar(Long id, Veiculo veiculo) {
        Optional<Veiculo> clienteBd = veiculosRepository.findById(id);
        if(clienteBd.isPresent()) {
            salvarVeiculoComCaracteristica(veiculo);
        }
    }

    @Override
    public void deletar(Long id) {
            veiculosRepository.deleteById(id);
    }

    @Override
    public Veiculo buscarPorRenavam(String renavam) {
        return veiculosRepository.buscarPorRenavan(renavam);
    }

    private void salvarVeiculoComCaracteristica(Veiculo veiculo){
        String marca = veiculo.getModelo_id().getMarca_id().getCode();
        String modelo = veiculo.getModelo_id().getCode();
        String ano = veiculo.getAno_id().getCode();
        String renavam = veiculo.getRenavam();
        VeiculosUtils veiculosUtils = new VeiculosUtils();
        veiculosUtils.validacaoDigitosRenavam(renavam);
        Caracteristica caracteristica = tabelaFipeService.consultaCaracteristica(marca, modelo, ano);
        veiculo.setCaracteristica_id(caracteristica);
        veiculo.setStatus(Status.PENDING);
        veiculo.setData(LocalDate.now());
        veiculosRepository.save(veiculo);
    }
}
