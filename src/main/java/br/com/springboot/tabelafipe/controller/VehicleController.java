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
@Controller("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;

    @Autowired
    private UserService userService;
    
    private String globalStatus;
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("/vehicle")
    public ModelAndView findUser(@ModelAttribute("alertMessage") @Nullable String alertMessage , @PathVariable("cpf") String cpf, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("task-vehicle");
        if(SELECTED_PAGE == null){
            SELECTED_PAGE = PAGE_NO;
        }
        ModelAndView mvaux = modelAndViewListAux(SELECTED_PAGE, mv);
        mvaux.addObject("alertMessage", alertMessage);
        SELECTED_PAGE = null;
        //redirectAttributes.addFlashAttribute("cpf", usuario);
        return mvaux;
    }

    @GetMapping("/vehicle/add-new-vehicle")
    public ModelAndView pageNewVehicle(){
        ModelAndView mv = new ModelAndView("new-vehicle");
        String message = "";
        return modelAndViewAux(mv, new VehicleDTO(), new UserDTO(), message);
    }

    @PostMapping(value = "/vehicle/add-or-update-vehicle/{cpf}")
    public ModelAndView addOrUpdateTask(final @Valid @RequestBody VehicleDTO vehicle,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes,
                                        @PathVariable(value = "cpf") String cpf){

        String message = "Error, please fill the form correctly";


        UserDTO userDTO = userService.getUserByCpf(cpf);
        List<VehicleDTO> vehicles = userDTO.getVehicleDTOList();

        if(vehicles == null){
            vehicles = new ArrayList<>();
        }

        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-vehicle");
            return modelAndViewAux(mv, vehicle, userDTO , message);
        }

        if(userDTO.getCpf() != null){

            vehicles.add(vehicle);
            userDTO.setVehicleDTOList(vehicles);
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("alertMessage","New Task was been successfully saved");
        }

        return new ModelAndView("redirect:/vehicle");
    }

    public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv){

        Page<VehicleDTO> page = vehicleService.getVehicleListPaginated(selectedPage, PAGE_SIZE, globalStatus);
        List<VehicleDTO> vehicleList = page.getContent();
        mv.addObject("vehicleDTOList", vehicleList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }

    public ModelAndView modelAndViewAux(ModelAndView mv, VehicleDTO vehicleDTO, UserDTO userDTO, String message){
        mv.addObject("vehicleDTO", vehicleDTO);
        mv.addObject("userDTO", userDTO);
        mv.addObject("models",vehicleService.getModels(vehicleDTO));
        mv.addObject("years",vehicleService.getYears(vehicleDTO));
        mv.addObject("fuels",vehicleService.getFuel(vehicleDTO));
        mv.addObject("brands",vehicleService.getBrands());
        mv.addObject("statusList", vehicleService.getStatus());
        mv.addObject("alertMessage", message);
        return mv;
    }

    @DeleteMapping("/vehicle/delete-vehicle/{id}")
    public ModelAndView deleteTask(@PathVariable Long id){
        vehicleService.deleteVehicle(id);
        ModelAndView mv = new ModelAndView("components/task-vehicle");
        return modelAndViewListAux(SELECTED_PAGE != null ? SELECTED_PAGE : 1, mv);
    }

    @GetMapping("vehicle/vehicle-by-status")
    public ModelAndView getTaskListByStatus(@RequestParam(name = "status", required = false) String status){
        ModelAndView mv = new ModelAndView("redirect:/vehicle");
        globalStatus = status;
        return mv;
    }

    @GetMapping("vehicle/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo){

        ModelAndView mv = new ModelAndView("components/task-vehicle");
        SELECTED_PAGE = pageNo;
        return modelAndViewListAux(pageNo, mv);

    }

}
