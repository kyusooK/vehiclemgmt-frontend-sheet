package com.posco.assignoperation.s20g01.domain.vehiclePerformance;

import com.posco.assignoperation.s20g01.domain.Period;
import com.posco.assignoperation.s20g01.domain.Purpose;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "VehiclePerformance_table")
@Data
public class VehiclePerformance {

    @Id
    private String registrationId;

    private String vehicleNumber;

    private Date registrationDate;

    private String departure;

    private String departureTime;

    private int accumulatedDistanceBefore;

    private String destination;

    private String arrivalTime;

    private int accumulatedDistanceAfter;

    private Double drivingDistance;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Embedded
    private Period period;

    public void registerDrivingLog(RegisterDrivingLogCommand command) {
        // 비즈니스 로직 구현
    }
}
