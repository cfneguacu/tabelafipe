package br.com.springboot.tabelafipe.service;


import br.com.springboot.tabelafipe.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "tabelafipe", url = "https://parallelum.com.br/fipe/api/v2/cars")
public interface FipeService {

    @RequestMapping(method = RequestMethod.GET, value = "/brands")
    List<BrandEntity> consultBrands();

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models")
    List<ModelEntity> consultModel(@PathVariable("brand") String brand);
    
    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models/{model}/years")
    List<YearEntity> consultVehicleList(@PathVariable("brand") String brand, @PathVariable("model") String model);

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models/{model}/years/{year}")
    CharacteristicEntity consultCharacteristic(@PathVariable("brand") String brand, @PathVariable("model") String model, @PathVariable("year") String year);

}
