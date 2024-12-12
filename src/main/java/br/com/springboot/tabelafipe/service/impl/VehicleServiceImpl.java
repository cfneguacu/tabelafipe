package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.adapter.VehicleDTOAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleEntityAdapter;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.*;
import br.com.springboot.tabelafipe.exceptions.UserNotFoundException;
import br.com.springboot.tabelafipe.exceptions.VehicleNotFoundException;
import br.com.springboot.tabelafipe.repository.UserRepository;
import br.com.springboot.tabelafipe.repository.VehicleRepository;
import br.com.springboot.tabelafipe.service.FipeService;
import br.com.springboot.tabelafipe.service.VehicleService;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    private final UserRepository userRepository;

    @Autowired
    private FipeService fipeService;

    @Autowired
    private VehicleService vehicleService;

    private final StatusConvert statusConvert;

    private final VehicleDTOAdapter vehicleDTOAdapter;

    private final VehicleEntityAdapter vehicleEntityAdapter;

    public VehicleServiceImpl(final StatusConvert statusConvert, final VehicleDTOAdapter vehicleDTOAdapter, final VehicleEntityAdapter vehicleEntityAdapter, final VehicleRepository vehicleRepository, final UserRepository userRepository) {

        this.statusConvert = statusConvert;
        this.vehicleDTOAdapter = vehicleDTOAdapter;
        this.vehicleEntityAdapter = vehicleEntityAdapter;
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }


    @Override
    public VehicleDTO getVehicleById(Long id) {
        final Optional<VehicleEntity> optionalTaskEntity = vehicleRepository.findById(id);
        if(optionalTaskEntity.isPresent()){
            return vehicleDTOAdapter.toDTO(optionalTaskEntity.get());
        }else{
            throw new VehicleNotFoundException("Vehicle with id"+id+" not found");
        }
    }

    @Override
    public void saveVehicle(String cpf, VehicleDTO vehicle) {

        try{

        UserEntity userEntity = userRepository.findByCpf(cpf);
        List<VehicleEntity> vehicleEntityList = userEntity.getVehicles();
        VehicleEntity vehicleEntity = vehicleEntityAdapter.toModel(vehicle);
        vehicleEntityList.add(vehicleEntity);
        userEntity.setVehicles(vehicleEntityList);
        userRepository.save(userEntity);

        } catch(UserNotFoundException re){
            throw re;
        }
    }

    @Override
    public void deleteVehicle(String cpf, Long id) throws Exception {


        UserEntity userEntity = userRepository.findByCpf(cpf);
        VehicleEntity vehicleEntity = vehicleRepository.findById(id).
                orElseThrow(()-> new Exception("Vehicle Not Found"));
        userEntity.getVehicles().remove(vehicleEntity);
        vehicleRepository.delete(vehicleEntity);
        userRepository.save(userEntity);
    }


    @Override
    public List<String> getStatus(){
        return Arrays.asList(Status.SUCCESS.getDescription(),Status.FAILURE.getDescription(),Status.PENDING.getDescription(),Status.VEHICLE_ALREADY_EXISTS.getDescription());
    }

    public List<VehicleDTO> getVehicleListByStatus(String strStatus){
        Status status = statusConvert.convertStatus(strStatus);
        if(status == null){
            return getVehicleList();
        }

        List<VehicleEntity> vehicleEntityList = vehicleRepository.findAllByStatusOrderBySubscriptionDateDesc(status);
        return vehicleEntityList.stream()
                .map(vehicleDTOAdapter::toDTO)
                .collect(Collectors.toList());
    }

    public Page<VehicleDTO> getVehicleListPaginated(int pageNo, int pageSize, String status){

        List<VehicleDTO> vehicleDTOList;
        Page<VehicleDTO> page;
        vehicleDTOList = getVehicleListByStatus(status);
        pageNo = 1;



        if(!vehicleDTOList.isEmpty()){
            if(vehicleDTOList.size() < pageSize){
                pageSize = vehicleDTOList.size();
            }
            Pageable pageable = PageRequest.of(0, pageSize);
            int start = (int)pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), vehicleDTOList.size());
            List<VehicleDTO> subList = vehicleDTOList.subList(start, end);
            page = new PageImpl<>(subList, pageable, vehicleDTOList.size());
        }else{
            page = Page.empty();
        }

        return page;
    }

    @Override
    public List<VehicleDTO> getVehicleList(){
        return vehicleRepository.findAllByOrderBySubscriptionDateDesc()
                .stream()
                .map(vehicleDTOAdapter::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<String> getBrands(){
        
        List<BrandDTO> brandsList = fipeService.consultBrands();
        List<String> brands = new ArrayList<>();
        for(BrandDTO brand : brandsList){
            brands.add(brand.getName());
        }
        
        return brands;
    }


    @Override
    public List<String> getModels(VehicleDTO vehicle){

        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        List<ModelDTO> modelsList = fipeService.consultModel(brand);
        List<String> models = new ArrayList<>();
        for(ModelDTO model : modelsList){
            models.add(model.getName());
        }

        return models;
    }


    @Override
    public List<String> getYears(VehicleDTO vehicle){

        String model = vehicle.getModelDTO().getCode();
        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        
        List<YearDTO> yearsList = fipeService.consultVehicleList(brand, model);
        List<String> years = new ArrayList<>();
        for(YearDTO year : yearsList){
            years.add(year.getName());
        }

        return years;
    }

    @Override
    public String getFuel(VehicleDTO vehicle){

        String model = vehicle.getModelDTO().getCode();
        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        String year = vehicle.getYearDTO().getCode();

        CharacteristicDTO characteristic = fipeService.consultCharacteristic(brand, model, year);

        return characteristic.getFuel();
    }


}
