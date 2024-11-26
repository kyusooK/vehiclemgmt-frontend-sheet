package com.posco.standardmanagement.s20g01.domain.vehicle;

import com.posco.standardmanagement.s20g01.domain.VehicleStatus;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class RegisterVehicleCommand {

    private String registrationNumber;
    private String make;
    private String model;
    private Integer year;
    private VehicleStatus status;
}
