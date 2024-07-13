package br.com.springboot.tabelafipe.controller;

import java.time.LocalDate;
import java.util.List;

//import javax.validation.Valid;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.service.TabelaFipeService;
import br.com.springboot.tabelafipe.service.VeiculoService;
import br.com.springboot.tabelafipe.utils.VeiculosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.tabelafipe.repository.VeiculosRepository;
import br.com.springboot.tabelafipe.status.Status;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class VeiculosController {
	
	@Autowired
	private VeiculoService veiculoService;

    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "veiculolistatodos")
    @ResponseBody
    public ResponseEntity<Iterable<Veiculo>> listaVeiculo(){
    	Iterable<Veiculo> veiculos = veiculoService.buscarTodos();
    	return new ResponseEntity<>(veiculos, HttpStatus.OK);
    
    }

   @PostMapping(value = "veiculosalvar")
   @ResponseBody
   public Status salvar(@RequestBody Veiculo veiculo) {
        veiculoService.inserir(veiculo);
        return Status.PENDING;

   }
   
   @DeleteMapping(value = "veiculodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	veiculoService.deletar(iduser);
    	return new ResponseEntity<String>("Veiculo deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "veiculobuscaruserId")
   @ResponseBody
    public ResponseEntity<Veiculo> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	Veiculo veiculo = veiculoService.buscarPorId(iduser);
    	return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }

    @GetMapping(value = "veiculobuscarPorRenavam")
    @ResponseBody
    public ResponseEntity<Veiculo> buscarPorRenavam(@RequestParam(name = "renavam") String renavam){
        Veiculo veiculo = veiculoService.buscarPorRenavam(renavam);
        return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }
   
   @PutMapping(value = "veiculoatualizar")
   @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Veiculo veiculo, @PathVariable("id") Long id){
    	
	   if(id == null) {
		   return new ResponseEntity<String>("Codigo de Veiculo não foi informado para atualização", HttpStatus.OK);
	   }
	   
	    veiculoService.atualizar(id, veiculo);
    	return new ResponseEntity<Veiculo>(veiculo, HttpStatus.OK);
    }

}
