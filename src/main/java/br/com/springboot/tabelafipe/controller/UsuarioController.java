package br.com.springboot.tabelafipe.controller;

import br.com.springboot.tabelafipe.model.Usuario;
import br.com.springboot.tabelafipe.model.Veiculo;
import br.com.springboot.tabelafipe.service.UsuarioService;
import br.com.springboot.tabelafipe.service.VeiculoService;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private String globalStatus;
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("add-new-user")
    public ModelAndView pageNewTask(){
        ModelAndView mv = new ModelAndView("new-user");
        String message = "";
        return modelAndViewAux(mv, new Usuario(), message);
    }

    @PostMapping("/add-or-update-user")
    public ModelAndView addOrUpdateTask(final @Valid Usuario usuario,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes){

        String message = "Error, please fill the form correctly";


        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-user");
            return modelAndViewAux(mv, usuario, message);
        }

        if(usuario.getId() == null){
            usuarioService.inserir(usuario);
            redirectAttributes.addFlashAttribute("alertMessage","New Task was been successfully saved");
        }else{
            usuarioService.atualizar(usuario);
            redirectAttributes.addFlashAttribute("alertMessage","Task was been successfully updated");
        }

        return new ModelAndView("redirect:/");
    }



    @DeleteMapping(value = "usuariodelete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
        usuarioService.deletar(iduser);
        return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }

    @GetMapping(value = "usuariobuscaruserId")
    @ResponseBody
    public ResponseEntity<Usuario> buscaruserId(@RequestParam(name = "iduser") Long iduser){
        Usuario usuario = usuarioService.buscarPorId(iduser);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    /*public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv){

        Page<TaskDto> page = usuarioService.getTaskListPaginated(selectedPage, PAGE_SIZE, globalStatus);
        List<TaskDto> taskDtoList = page.getContent();
        mv.addObject("taskDtoList", taskDtoList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }*/

    public ModelAndView modelAndViewAux(ModelAndView mv, Usuario usuario, String message){
        mv.addObject("usuario", usuario);
       // mv.addObject("priorities",taskService.getPriorities());
       // mv.addObject("statusList",taskService.getStatus());
        mv.addObject("alertMessage", message);
        return mv;
    }

}
