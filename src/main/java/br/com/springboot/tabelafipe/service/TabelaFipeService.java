package br.com.springboot.tabelafipe.service;


import br.com.springboot.tabelafipe.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "tabelafipe", url = "https://parallelum.com.br/fipe/api/v2/cars")
public interface TabelaFipeService {

    @RequestMapping(method = RequestMethod.GET, value = "/brands")
    List<Marca> consultaMarcas();

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{marca}/models")
    List<Modelo> consultaModelo(@PathVariable("marca") String marca);
    
    @RequestMapping(method = RequestMethod.GET, value = "/brands/{marca}/models/{modelo}/years")
    List<Ano> consultaListaVeiculos(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo);

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{marca}/models/{modelo}/years/{ano}")
    Caracteristica consultaCaracteristica(@PathVariable("marca") String marca, @PathVariable("modelo") String modelo, @PathVariable("ano") String ano);

}
