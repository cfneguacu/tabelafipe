package br.com.springboot.tabelafipe.component;

import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.repository.VehicleRepository;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleJob {

    private VehicleRepository repository;

    @Scheduled(fixedDelay = 60_000)
    public void execute(){

        List<VehicleEntity> veiculos = repository.findAllByStatus(Status.PENDING);
        veiculos.forEach(veiculo -> {
            veiculo.setStatus(Status.SUCCESS);
            repository.save(veiculo);
        });
    }
}
