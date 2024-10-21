package br.com.springboot.tabelafipe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import javax.validation.Valid;
import br.com.springboot.tabelafipe.adapter.UserDTOAdapter;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.service.UserService;
import br.com.springboot.tabelafipe.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 *
 * A sample greetings controller to return greeting text
 */
@Controller("/find-user/{cpf}")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;

    @Autowired
    private UserService userService;
    

    private static String USER_CPF = "";
    private String globalStatus;
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("/find-user/{cpf}")
    public ModelAndView findUser(@ModelAttribute("alertMessage") @Nullable String alertMessage , @PathVariable("cpf") String cpf, RedirectAttributes redirectAttributes){
        //UserEntity userEntity = userService.findUser(cpf);
        ModelAndView mv = new ModelAndView("task-vehicle");
        if(SELECTED_PAGE == null){
            SELECTED_PAGE = PAGE_NO;
        }
        ModelAndView mvaux = modelAndViewListAux(SELECTED_PAGE, mv);
        mvaux.addObject("alertMessage", alertMessage);
        SELECTED_PAGE = null;
        USER_CPF = cpf;
        //redirectAttributes.addFlashAttribute("cpf", usuario);
        return mvaux;
    }

    @GetMapping("add-new-vehicle")
    public ModelAndView pageNewVehicle(){
        ModelAndView mv = new ModelAndView("new-vehicle");
        String message = "";
        return modelAndViewAux(mv, new VehicleDTO(), message);
    }

    @PostMapping("/add-or-update-vehicle")
    public ModelAndView addOrUpdateTask(final @Valid VehicleDTO vehicle,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes){

        String message = "Error, please fill the form correctly";

        List<VehicleDTO> vehicles = new ArrayList<>();
        UserDTO userDTO = userService.getUserByCpf(USER_CPF);

        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-vehicle");
            return modelAndViewAux(mv, vehicle, message);
        }

        if(vehicle.getId() != null){

            vehicles.add(vehicle);
            userDTO.setVehicles(vehicles);
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","New Task was been successfully saved");
        }

        return new ModelAndView("redirect:/task-vehicle");
    }

    public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv){

        Page<VehicleDTO> page = vehicleService.getVehicleListPaginated(selectedPage, PAGE_SIZE, globalStatus);
        List<VehicleDTO> vehicleList = page.getContent();
        mv.addObject("vehicleList", vehicleList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }

    public ModelAndView modelAndViewAux(ModelAndView mv, VehicleDTO vehicle, String message){
        mv.addObject("vehicle", vehicle);
        mv.addObject("models",vehicleService.getModels(vehicle));
        mv.addObject("years",vehicleService.getYears(vehicle));
        mv.addObject("fuel",vehicleService.getFuel(vehicle));
        mv.addObject("brands",vehicleService.getBrands());
        mv.addObject("statusList", vehicleService.getStatus());
        mv.addObject("alertMessage", message);
        return mv;
    }

    @DeleteMapping("delete-vehicle/{id}")
    public ModelAndView deleteTask(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        ModelAndView mv = new ModelAndView("components/task-vehicle");
        return modelAndViewListAux(SELECTED_PAGE != null ? SELECTED_PAGE : 1, mv);
    }

    @GetMapping("vehicle-by-status")
    public ModelAndView getTaskListByStatus(@RequestParam(name = "status", required = false) String status){
        ModelAndView mv = new ModelAndView("redirect:/");
        globalStatus = status;
        return mv;
    }

    @GetMapping("/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo){

        ModelAndView mv = new ModelAndView("components/task-card");
        SELECTED_PAGE = pageNo;
        return modelAndViewListAux(pageNo, mv);

    }

}
