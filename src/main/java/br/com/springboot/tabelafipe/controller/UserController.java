package br.com.springboot.tabelafipe.controller;

import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;

@Controller("/")
public class UserController {

    @Autowired
    private UserService userService;

    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("/")
    public ModelAndView home(@ModelAttribute("alertMessage") @Nullable String alertMessage){
        ModelAndView mv = new ModelAndView("index");
        if(SELECTED_PAGE == null){
            SELECTED_PAGE = PAGE_NO;
        }
        ModelAndView mvaux = modelAndViewListAux(SELECTED_PAGE, mv);
        mvaux.addObject("alertMessage", alertMessage);
        SELECTED_PAGE = null;
        return mvaux;

    }

    @GetMapping("add-new-user")
    public ModelAndView pageNewUser(){
        ModelAndView mv = new ModelAndView("new-user");
        String message = "";
        return modelAndViewAux(mv, new UserDTO(), message);
    }

    @PostMapping("/add-or-update-user")
    public ModelAndView addOrUpdateUser(final @Valid @RequestBody UserDTO userDTO,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes){

        String message = "Error, please fill the form correctly";


        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-user");
            return modelAndViewAux(mv, userDTO, message);
        }

        if(userDTO.getId() == null){
            userService.saveUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","New User was been successfully saved");
        }else{
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","User was been successfully updated");
        }

        return new ModelAndView("redirect:/");
    }

    public ModelAndView modelAndViewAux(ModelAndView mv, UserDTO userDTO, String message){
        mv.addObject("userDTO", userDTO);
        mv.addObject("alertMessage", message);
        return mv;
    }

    public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv){

        Page<UserDTO> page = userService.getUserListPaginated(selectedPage, PAGE_SIZE);
        List<UserDTO> vehicleList = page.getContent();
        mv.addObject("userDTOList", vehicleList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }


    @DeleteMapping("delete-user/{id}")
    public ModelAndView deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        ModelAndView mv = new ModelAndView("components/task-user");
        return modelAndViewListAux(SELECTED_PAGE != null ? SELECTED_PAGE : 1, mv);
    }

    @GetMapping("/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo){

        ModelAndView mv = new ModelAndView("components/task-card");
        SELECTED_PAGE = pageNo;
        return modelAndViewListAux(pageNo, mv);

    }

}
