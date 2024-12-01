package br.com.springboot.tabelafipe.unittests;

import br.com.springboot.tabelafipe.adapter.UserEntityAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleDTOAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleEntityAdapter;
import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.*;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.repository.UserRepository;
import br.com.springboot.tabelafipe.repository.VehicleRepository;
import br.com.springboot.tabelafipe.service.VehicleService;
import br.com.springboot.tabelafipe.service.impl.UserServiceImpl;
import br.com.springboot.tabelafipe.service.impl.VehicleServiceImpl;
import br.com.springboot.tabelafipe.status.Status;
import br.com.springboot.tabelafipe.utils.VehicleUtils;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestVehicleService {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StatusConvert statusConvert;

    @Mock
    private InstantConvert instantConvert;

    @Mock
    private UserEntityAdapter userEntityAdapter;

    @Mock
    private VehicleDTOAdapter vehicleDTOAdapter;

    @Mock
    private VehicleEntityAdapter vehicleEntityAdapter;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @InjectMocks
    private UserServiceImpl userService;


    public TestVehicleService() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveVehicle() {
        final UserEntity userEntity = new UserEntity();
        final UserDTO userDTO = new UserDTO();
        final List<VehicleDTO> vehicleDTOList;
        final VehicleDTO vehicleDTO = new VehicleDTO();
        final VehicleEntity vehicleEntity = new VehicleEntity();
       // userEntity.setCpf("29328172802");
       // userEntity.setEmail("teste@teste.com.br");
       // userEntity.setName("Claudio");
        /*VehicleEntity vehicleEntity = VehicleEntity.builder()
                .yearEntity(YearEntity.builder()
                        .name("1992")
                        .code("1992-1")
                        .build())
                .id(1L)
                .color("blue")
                .licensePlate("ddd299")
                .subscriptionDate(Instant.now())
                .modelEntity(ModelEntity.builder()
                        .id(1L)
                        .name("Acura")
                        .code("2")
                        .brandEntity(BrandEntity.builder()
                                .id(1L)
                                .code("2")
                                .name("DSX")
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .brand("Acura")
                        .model("DSX")
                        .price("R$ 20.000,00")
                        .modelYear("1992")
                        .fuel("Gasolina")
                        .build())
                .renavam("12345678901")
                .status(Status.PENDING)
                .build();*/
        //VehicleEntity vehicleEntity = vehicleEntityAdapter.toModel(vehicleDTO);
        vehicleDTOList = Arrays.asList(vehicleDTO, vehicleDTO, vehicleDTO);
        userDTO.setVehicleDTOList(vehicleDTOList);
        when(vehicleEntityAdapter.toModel(vehicleDTO)).thenReturn(vehicleEntity);
        when(userEntityAdapter.toModel(userDTO)).thenReturn(userEntity);
        userService.saveUser(userDTO);
        verify(userEntityAdapter, times(1)).toModel(userDTO);
        verify(userRepository, times(1)).save(userEntity);

    }

    @Test
    public void testGetVehicleById() {
        Long id = 1L;
        Instant instant = Instant.now();
        VehicleEntity vehicleEntity = VehicleEntity.builder()
                .yearEntity(YearEntity.builder()
                        .build())
                .subscriptionDate(instant)
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .build())
                .status(Status.PENDING)
                .build();;
        when(vehicleRepository.findById(id)).thenReturn(Optional.of(vehicleEntity));
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .yearDTO(YearDTO.builder()
                        .build())
                .subscriptionDate("2024-11-30")
                .modelDTO(ModelDTO.builder()
                        .brandDTO(BrandDTO.builder()
                                .build())
                        .build())
                .characteristicDTO(CharacteristicDTO.builder()
                        .build())
                .statusClass("badge badge-primary")
                .status("Pending")
                .build();;
        when(vehicleDTOAdapter.toDTO(vehicleEntity)).thenReturn(vehicleDTO);
        VehicleDTO vehicleDTOResult = vehicleService.getVehicleById(id);

        assertNotNull(vehicleDTOResult);
        assertEquals(vehicleDTO, vehicleDTOResult);

        verify(vehicleRepository, times(1)).findById(id);
        verify(vehicleDTOAdapter, times(1)).toDTO(vehicleEntity);

    }

    @Test
    public void testDeleteVehicle() throws Exception {

        Random random = new Random();

       Long id = random.nextLong();


        VehicleEntity vehicleEntity1 = VehicleEntity.builder()
                .id(id)
                .yearEntity(YearEntity.builder()
                        .build())
                .subscriptionDate(Instant.now())
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .build())
                .status(Status.PENDING)
                .build();

        VehicleEntity vehicleEntity2 = VehicleEntity.builder()
                .id(random.nextLong())
                .yearEntity(YearEntity.builder()
                        .build())
                .subscriptionDate(Instant.now())
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .build())
                .status(Status.PENDING)
                .build();

        VehicleEntity vehicleEntity3 = VehicleEntity.builder()
                .id(random.nextLong())
                .yearEntity(YearEntity.builder()
                        .build())
                .subscriptionDate(Instant.now())
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .build())
                .status(Status.PENDING)
                .build();

        List<VehicleEntity> vehicleEntityList = Arrays.asList(vehicleEntity1, vehicleEntity2, vehicleEntity3);

        UserEntity userEntity = UserEntity.builder()
                .vehicles(vehicleEntityList)
                .build();

       userRepository.save(userEntity);
       vehicleRepository.deleteById(id);

        verify(userRepository, times(1)).save(userEntity);
        verify(vehicleRepository, times(1)).deleteById(id);
    }

    /*@Test
    public void testUpdateVehicle() {
        VehicleDTO vehicleDTO = VehicleDTO.builder()
                .yearDTO(YearDTO.builder()
                        .name("1992")
                        .code("1992-1")
                        .build())
                .id(2L)
                .color("Blue")
                .licensePlate("dddd3333")
                .subscriptionDate(instantConvert.convertInstantToString(Instant.now()))
                .modelDTO(ModelDTO.builder()
                        .id(1L)
                        .name("Acura")
                        .code("1")
                        .brandDTO(BrandDTO.builder()
                                .id(1L)
                                .code("1")
                                .name("DSX")
                                .build())
                        .build())
                .characteristicDTO(CharacteristicDTO.builder()
                        .brand("Acura")
                        .model("DSX")
                        .price("R$ 12.000")
                        .modelYear("1992")
                        .fuel("Gasolina")
                        .build())
                .renavam("12345678901")
                .status("Pending")
                .build();
        VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(vehicleDTO.getId());
        when(vehicleRepository.findById(vehicleDTO.getId())).thenReturn(Optional.of(vehicleEntity));

        vehicleService.updateVehicle(vehicleDTO);

        verify(vehicleRepository, times(1)).findById(vehicleDTO.getId());
        verify(vehicleRepository, times(1)).save(vehicleEntity);

        assertEquals("Cláudio", vehicleEntity.getName());
        assertEquals("29328172802", vehicleEntity.getCpf());
        assertEquals("cfneguacu@hotmail.com", vehicleEntity.getEmail());


    }*/

    @Test
    public void testGetVehicleList() {

        VehicleEntity vehicleEntity = VehicleEntity.builder()
                .id(1L)
                .yearEntity(YearEntity.builder()
                        .build())
                .subscriptionDate(instantConvert.convertStringToInstant("2024-11-30"))
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .build())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .build())
                .status(Status.PENDING)
                .build();
        List<VehicleEntity> vehicleEntityList = Arrays.asList(vehicleEntity, vehicleEntity, vehicleEntity);
        when(vehicleRepository.findAllByOrderBySubscriptionDateDesc()).thenReturn(vehicleEntityList);

        VehicleDTO vehicleDTO = new VehicleDTO();

        when(vehicleDTOAdapter.toDTO(vehicleEntity)).thenReturn(vehicleDTO);

        List<VehicleDTO> vehicleDTOList = vehicleService.getVehicleList();

        assertNotNull(vehicleDTOList);
        assertEquals(3, vehicleDTOList.size());
        verify(vehicleRepository, times(1)).findAllByOrderBySubscriptionDateDesc();
        verify(vehicleDTOAdapter, times(3)).toDTO((VehicleEntity) any());
    }
}
