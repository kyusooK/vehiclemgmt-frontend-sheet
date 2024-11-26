package com.posco.assignoperation.s20g01.domain.vehiclePerformance;

import com.posco.assignoperation.s20g01.domain.Period;
import com.posco.assignoperation.s20g01.domain.Purpose;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class ModifyPerformanceCommand {

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
    private Purpose purpose;
    private Period period;
}
