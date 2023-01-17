package br.com.springboot.tabelafipe.controller;

import java.util.List;

//import javax.validation.Valid;
import br.com.springboot.tabelafipe.utils.VeiculosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.tabelafipe.model.Veiculos;
import br.com.springboot.tabelafipe.repository.VeiculosRepository;
import br.com.springboot.tabelafipe.status.Status;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class VeiculosController {
	
	@Autowired
	private VeiculosRepository veiculoRepository;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "veiculolistatodos")
    @ResponseBody
    public ResponseEntity<List<Veiculos>> listaVeiculo(){
    	List<Veiculos> veiculos = veiculoRepository.findAll();
    	return new ResponseEntity<>(veiculos, HttpStatus.OK);
    
    }

   @PostMapping(value = "veiculosalvar")
   @ResponseBody
   public Status salvar(@RequestBody Veiculos veiculo) {

        VeiculosUtils veiculosUtils = new VeiculosUtils();
        veiculosUtils.validacaoDigitosRenavam(veiculo.getRenavam());
        veiculo.setStatus(Status.PENDING);
        veiculoRepository.save(veiculo);

       return Status.PENDING;

   }
   
   @DeleteMapping(value = "veiculodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	veiculoRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Veiculo deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "veiculobuscaruserId")
   @ResponseBody
    public ResponseEntity<Veiculos> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	Veiculos veiculo = veiculoRepository.findById(iduser).get();
    	return new ResponseEntity<Veiculos>(veiculo, HttpStatus.OK);
    }

    @GetMapping(value = "veiculobuscarPorRenavam")
    @ResponseBody
    public ResponseEntity<Veiculos> buscarPorRenavam(@RequestParam(name = "renavam") String renavam){
        Veiculos veiculo = veiculoRepository.buscarPorRenavan(renavam);
        return new ResponseEntity<Veiculos>(veiculo, HttpStatus.OK);
    }
   
   @PutMapping(value = "veiculoatualizar")
   @ResponseBody
    public ResponseEntity<?> buscaruserId(@RequestBody Veiculos veiculo){
    	
	   if(veiculo.getCodigo() == null) {
		   return new ResponseEntity<String>("Codigo de Veiculo não foi informado para atualização", HttpStatus.OK);
	   }
	   
	   Veiculos user = veiculoRepository.saveAndFlush(veiculo);
    	return new ResponseEntity<Veiculos>(user, HttpStatus.OK);
    }
   
}
