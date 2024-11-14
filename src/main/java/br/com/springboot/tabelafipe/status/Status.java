package br.com.springboot.tabelafipe.status;

import lombok.Getter;
import lombok.Setter;



@Getter
public enum Status {
    SUCCESS("Success"),
    VEHICLE_ALREADY_EXISTS("Vehicle Already Exists"),
    FAILURE("Failure"),
    PENDING("Pending");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public void setDescription(String description){
        this.description = description;
    }

}

