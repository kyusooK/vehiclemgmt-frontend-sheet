package com.posco.standardmanagement.s20g01.domain.vehicle;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateVehicleDetailsCommand {

    private Long id;
    private String registrationNumber;
    private String make;
    private String model;
    private Integer year;
}
