package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.adapter.VehicleDTOAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleEntityAdapter;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.*;
import br.com.springboot.tabelafipe.repository.VehicleRepository;
import br.com.springboot.tabelafipe.service.FipeService;
import br.com.springboot.tabelafipe.service.VehicleService;
import br.com.springboot.tabelafipe.status.Status;
import br.com.springboot.tabelafipe.utils.VehicleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private FipeService fipeService;

    private StatusConvert statusConvert;

    private VehicleDTOAdapter vehicleDTOAdapter;

    private VehicleEntityAdapter vehicleEntityAdapter;

    @Override
    public Iterable<VehicleEntity> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public void saveVehicle(VehicleDTO vehicle) {
        try {
           // final VehicleEntity taskEntity = vehicleEntityAdapter.toModel(vehicle);
            saveVehicleWithCharacteristic(vehicle);
        } catch(RuntimeException re){
            throw re;
        }
    }

    @Override
    public void updateVehicle(VehicleEntity vehicle) {

    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public VehicleEntity findByRenavam(String renavam) {
        return vehicleRepository.findByRenavan(renavam);
    }

    @Override
    public List<String> getStatus(){
        return Arrays.asList(Status.SUCCESS.toString(),Status.FAILURE.toString(),Status.PENDING.toString(),Status.VEHICLE_ALREADY_EXISTS.toString());
    }

    public List<VehicleDTO> getVehicleListByStatus(String strStatus){
        Status status = statusConvert.convertStatus(strStatus);
        if(status == null){
            return getVehicleList();
        }

        List<VehicleEntity> taskEntityList = vehicleRepository.findAllByStatusOrderByDateDesc(status);
        return taskEntityList.stream()
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
            Pageable pageable = PageRequest.of(pageNo -1, pageSize);
            int start = (int)pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), vehicleDTOList.size());
            List<VehicleDTO> subList = vehicleDTOList.subList(start, end);
            page = new PageImpl<>(subList, pageable, vehicleDTOList.size());
        }else{
            page = Page.empty();
        }

        return page;
    }
    public List<VehicleDTO> getVehicleList(){
        return vehicleRepository.findAllByOrderByDateDesc()
                .stream()
                .map(vehicleDTOAdapter::toDTO)
                .collect(Collectors.toList());
    }


    public List<String> getBrands(){
        
        List<BrandEntity> brandsList = fipeService.consultBrands();
        List<String> brands = new ArrayList<>();
        for(BrandEntity brand : brandsList){
            brands.add(brand.getName());
        }
        
        return brands;
    }


    public List<String> getModels(VehicleDTO vehicle){
        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        List<ModelEntity> modelsList = fipeService.consultModel(brand);
        List<String> models = new ArrayList<>();
        for(ModelEntity model : modelsList){
            models.add(model.getName());
        }

        return models;
    }

    public List<String> getYears(VehicleDTO vehicle){

        String model = vehicle.getModelDTO().getCode();
        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        
        List<YearEntity> yearsList = fipeService.consultVehicleList(brand, model);
        List<String> years = new ArrayList<>();
        for(YearEntity year : yearsList){
            years.add(year.getName());
        }

        return years;
    }

    public String getFuel(VehicleDTO vehicle){

        String model = vehicle.getModelDTO().getCode();
        String brand = vehicle.getModelDTO().getBrandDTO().getCode();
        String year = vehicle.getYearDTO().getCode();

        CharacteristicEntity characteristic = fipeService.consultCharacteristic(brand, model, year);

        return characteristic.getFuel();
    }


    private void saveVehicleWithCharacteristic(VehicleDTO vehicle){
        //try {

            String brand = vehicle.getModelDTO().getBrandDTO().getCode();
            String model = vehicle.getModelDTO().getCode();
            String year = vehicle.getYearDTO().getCode();
            String renavam = vehicle.getRenavam();
            String licensePlate = vehicle.getLicensePlate();
            String relay;
            boolean activeRelay;
            int activeRelayTemp = getActiveRelayTemp(licensePlate);

            if (activeRelayTemp == LocalDate.now().getDayOfWeek().getValue()) {
                relay = DayOfWeek.of(activeRelayTemp).name();
                activeRelay = true;

            } else {
                relay = DayOfWeek.of(activeRelayTemp).name();
                activeRelay = false;
            }


            VehicleUtils vehicleUtils = new VehicleUtils();
            vehicleUtils.digitValidationRenavam(renavam);
            CharacteristicEntity characteristicEntity = fipeService.consultCharacteristic(brand, model, year);
            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.setCharacteristicEntity(characteristicEntity);
            vehicleEntity.setDate(Instant.now());
            vehicleEntity.setRelay(relay);
            vehicleEntity.setActiveRelay(activeRelay);
            vehicleEntity.setStatus(Status.PENDING);

        vehicleRepository.save(vehicleEntity);
    }

    private static int getActiveRelayTemp(String licensePlate) {
        int activeRelayTemp;

        if(licensePlate.charAt(licensePlate.length()-1)==0|| licensePlate.charAt(licensePlate.length()-1)==1)

        {
            activeRelayTemp = 1;
        }else if(licensePlate.charAt(licensePlate.length()-1)==2|| licensePlate.charAt(licensePlate.length()-1)==3)

        {
            activeRelayTemp = 2;
        }else if(licensePlate.charAt(licensePlate.length()-1)==4|| licensePlate.charAt(licensePlate.length()-1)==5)

        {
            activeRelayTemp = 3;
        }else if(licensePlate.charAt(licensePlate.length()-1)==6|| licensePlate.charAt(licensePlate.length()-1)==7)

        {
            activeRelayTemp = 4;
        }else

        {
            activeRelayTemp = 5;
        }
        return activeRelayTemp;
    }

}
