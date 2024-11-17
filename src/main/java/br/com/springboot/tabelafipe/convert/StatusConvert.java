package br.com.springboot.tabelafipe.convert;

import br.com.springboot.tabelafipe.status.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusConvert {

    public Status convertStatus(String status) {

          switch (status) {
              case "Vehicle Already Exists" -> {
                  return Status.VEHICLE_ALREADY_EXISTS;
              }
              case "Success" -> {
                  return Status.SUCCESS;
              }
              case "Failure" -> {
                  return Status.FAILURE;
              }
              case "Pending" -> {
                  return Status.PENDING;
              }
          }

        return null;
    }
}