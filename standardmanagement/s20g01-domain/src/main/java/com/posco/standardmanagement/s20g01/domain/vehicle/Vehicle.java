package com.posco.standardmanagement.s20g01.domain.vehicle;

import com.posco.standardmanagement.s20g01.domain.VehicleStatus;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Vehicle_table")
@Data
public class Vehicle {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    private String registrationNumber;

    private String make;

    private String model;

    private Integer year;

    private Double driverDistance;

    public void updateVehicleStatus(UpdateVehicleStatusCommand command) {
        // 비즈니스 로직 구현
    }
}
