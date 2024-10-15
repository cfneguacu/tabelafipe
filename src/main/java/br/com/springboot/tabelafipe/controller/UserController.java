package br.com.springboot.tabelafipe.controller;

import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private String globalStatus;
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("/")
    public ModelAndView home(@ModelAttribute("alertMessage") @Nullable String alertMessage){
        return new ModelAndView("index");

    }

    @GetMapping("add-new-user")
    public ModelAndView pageNewTask(){
        ModelAndView mv = new ModelAndView("new-user");
        String message = "";
        return modelAndViewAux(mv, new UserDTO(), message);
    }

    @PostMapping("/add-or-update-user")
    public ModelAndView addOrUpdateTask(final @Valid UserDTO userDTO,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes){

        String message = "Error, please fill the form correctly";


        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-user");
            return modelAndViewAux(mv, userDTO, message);
        }

        if(userDTO.getId() == null){
            userService.saveUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","New Task was been successfully saved");
        }else{
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","Task was been successfully updated");
        }

        return new ModelAndView("redirect:/");
    }

    public ModelAndView modelAndViewAux(ModelAndView mv, UserDTO userDTO, String message){
        mv.addObject("user", userDTO);
        mv.addObject("alertMessage", message);
        return mv;
    }

}
