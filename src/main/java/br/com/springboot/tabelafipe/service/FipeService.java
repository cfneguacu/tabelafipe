package br.com.springboot.tabelafipe.service;

import br.com.springboot.tabelafipe.dto.BrandDTO;
import br.com.springboot.tabelafipe.dto.CharacteristicDTO;
import br.com.springboot.tabelafipe.dto.ModelDTO;
import br.com.springboot.tabelafipe.dto.YearDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "tabelafipe", url = "https://fipe.parallelum.com.br/api/v2/cars")
public interface FipeService {

    @RequestMapping(method = RequestMethod.GET, value = "/brands")
    List<BrandDTO> consultBrands();

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models")
    List<ModelDTO> consultModel(@PathVariable("brand") String brand);
    
    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models/{model}/years")
    List<YearDTO> consultVehicleList(@PathVariable("brand") String brand, @PathVariable("model") String model);

    @RequestMapping(method = RequestMethod.GET, value = "/brands/{brand}/models/{model}/years/{year}")
    CharacteristicDTO consultCharacteristic(@PathVariable("brand") String brand, @PathVariable("model") String model, @PathVariable("year") String year);

}
