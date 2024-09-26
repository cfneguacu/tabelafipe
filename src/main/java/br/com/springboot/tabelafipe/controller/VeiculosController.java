package br.com.springboot.tabelafipe.controller;

import java.time.LocalDate;
import java.util.List;

//import javax.validation.Valid;
import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.service.TabelaFipeService;
import br.com.springboot.tabelafipe.service.UsuarioService;
import br.com.springboot.tabelafipe.service.VeiculoService;
import br.com.springboot.tabelafipe.service.impl.UsuarioServiceImpl;
import br.com.springboot.tabelafipe.utils.VeiculosUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.tabelafipe.repository.VeiculosRepository;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 *
 * A sample greetings controller to return greeting text
 */
@Controller("/")
public class VeiculosController {
	
	@Autowired
	private VeiculoService veiculoService;

    @Autowired
    private UsuarioService usuarioService;

    private static String USUARIO_CPF = "";

    @GetMapping("/find-user/{cpf}")
    public ModelAndView findUser(@PathVariable("cpf") String cpf, RedirectAttributes redirectAttributes){
        Usuario usuario = usuarioService.buscarUsuario(cpf);
        USUARIO_CPF = cpf;
        redirectAttributes.addFlashAttribute("cpf", usuario);
        return new ModelAndView("redirect:/find-user");
    }

    @GetMapping(value = "veiculolistatodos")
    @ResponseBody
    public ResponseEntity<Iterable<Veiculo>> listaVeiculo(){
    	Iterable<Veiculo> veiculos = veiculoService.buscarTodos();
    	return new ResponseEntity<>(veiculos, HttpStatus.OK);
    
    }

   @PostMapping(value = "veiculosalvar")
   public Status salvar(@RequestBody Veiculo veiculo) {
        Usuario usuario = usuarioService.buscarUsuario(USUARIO_CPF);
        veiculo.setUsuario_id(usuario.getId());
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
    public ResponseEntity<List<Veiculo>> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	List<Veiculo> veiculo = veiculoService.buscarPorId(iduser);
    	return new ResponseEntity<List<Veiculo>>(veiculo, HttpStatus.OK);
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

    public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv){

        Page<Veiculo> page = usuarioService.getTaskListPaginated(selectedPage, PAGE_SIZE, globalStatus);
        List<Veiculo> veiculoList = page.getContent();
        mv.addObject("veiculoList", veiculoList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }

}
