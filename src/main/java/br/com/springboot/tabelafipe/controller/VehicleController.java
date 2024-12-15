package br.com.springboot.tabelafipe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//import javax.validation.Valid;
import br.com.springboot.tabelafipe.adapter.UserDTOAdapter;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.service.FipeService;
import br.com.springboot.tabelafipe.service.UserService;
import br.com.springboot.tabelafipe.service.VehicleService;
import br.com.springboot.tabelafipe.service.impl.UserServiceImpl;
import br.com.springboot.tabelafipe.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


/**
 *
 * A sample greetings controller to return greeting text
 */
@Controller("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleServiceImpl vehicleService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FipeService fipeService;
    
    private String globalStatus = "Pending";
    private static final int PAGE_SIZE = 10;
    private static final int PAGE_NO = 1;
    private Integer SELECTED_PAGE;

    @GetMapping("/vehicle/{cpf}")
    public ModelAndView findUser(@ModelAttribute("alertMessage") @Nullable String alertMessage , RedirectAttributes redirectAttributes, @PathVariable("cpf") String cpf){
        ModelAndView mv = new ModelAndView("vehicle-index");
        if(SELECTED_PAGE == null){
            SELECTED_PAGE = PAGE_NO;
        }
        ModelAndView mvaux = modelAndViewListAux(SELECTED_PAGE, mv, cpf);
        mvaux.addObject("alertMessage", alertMessage);
        SELECTED_PAGE = null;
        //redirectAttributes.addFlashAttribute("cpf", usuario);
        return mvaux;
    }

    @GetMapping("/vehicle/add-new-vehicle/{cpf}")
    public ModelAndView pageNewVehicle(@PathVariable(value = "cpf") String cpf){
        ModelAndView mv = new ModelAndView("new-vehicle");
        String message = "";
        return modelAndViewAux(mv, new VehicleDTO(), cpf, message);
    }

    @PostMapping("/vehicle/add-or-update-vehicle/{cpf}")
    public ModelAndView addOrUpdateTask(final @Valid VehicleDTO vehicle,
                                        final BindingResult bindResult,
                                        final RedirectAttributes redirectAttributes,
                                        @PathVariable(value = "cpf") String cpf){

        String message = "Error, please fill the form correctly";


        if(bindResult.hasErrors()){
            ModelAndView mv = new ModelAndView("new-vehicle");
            return modelAndViewAux(mv, vehicle, cpf , message);
        }

        if(vehicle != null){
            String model = vehicle.getModelDTO().getCode();
            String brand = vehicle.getModelDTO().getBrandDTO().getCode();
            String year = vehicle.getYearDTO().getCode();
            CharacteristicDTO characteristicDTO = fipeService.consultCharacteristic(brand,model,year);
            vehicle.setCharacteristicDTO(characteristicDTO);
            vehicleService.saveVehicle(cpf, vehicle);
            redirectAttributes.addFlashAttribute("alertMessage","New Vehicle was been successfully saved");

        }

        return new ModelAndView("redirect:/vehicle");
    }

    public ModelAndView modelAndViewListAux(int selectedPage, ModelAndView mv, String cpf ){

        Page<VehicleDTO> page = vehicleService.getVehicleListPaginated(selectedPage, PAGE_SIZE, globalStatus, cpf);
        List<VehicleDTO> vehicleList = page.getContent();
        mv.addObject("vehicleDTOList", vehicleList);
        mv.addObject("currentPage", selectedPage);
        mv.addObject("totalPages", page.getTotalPages());
        mv.addObject("totalItens", page.getTotalElements());
        return mv;
    }

    public ModelAndView modelAndViewAux(ModelAndView mv, VehicleDTO vehicleDTO, String cpf, String message){

        mv.addObject("vehicleDTO", vehicleDTO);
        mv.addObject("cpf", cpf);
        mv.addObject("statusList", vehicleService.getStatus());
        mv.addObject("alertMessage", message);
        return mv;
    }

    @DeleteMapping("/vehicle/delete-vehicle/{cpf}/{id}")
    public ModelAndView deleteTask(@PathVariable String cpf, @PathVariable Long id) throws Exception {
        vehicleService.deleteVehicle(cpf,id);
        ModelAndView mv = new ModelAndView("/components/task-vehicle");
        return modelAndViewListAux(SELECTED_PAGE != null ? SELECTED_PAGE : 1, mv, cpf);
    }

    @GetMapping("/vehicle/vehicle-by-status")
    public ModelAndView getTaskListByStatus(@RequestParam(name = "status", required = false) String status){
        ModelAndView mv = new ModelAndView("redirect:/vehicle");
        globalStatus = status;
        return mv;
    }

    @GetMapping("/vehicle/{cpf}/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value="cpf") String cpf, @PathVariable(value = "pageNo") int pageNo){

        ModelAndView mv = new ModelAndView("/components/task-vehicle");
        SELECTED_PAGE = pageNo;
        return modelAndViewListAux(pageNo, mv, cpf);

    }

}
