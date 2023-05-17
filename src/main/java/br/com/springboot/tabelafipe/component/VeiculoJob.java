package br.com.springboot.tabelafipe.component;

import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.repository.VeiculosRepository;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VeiculoJob {

    private VeiculosRepository repository;

    @Scheduled(fixedDelay = 60_000)
    public void execute(){

        List<Veiculo> veiculos = repository.findAllByStatus(Status.PENDING);
        veiculos.forEach(veiculo -> {
            veiculo.setStatus(Status.SUCCESS);
            repository.save(veiculo);
        });
    }
}
