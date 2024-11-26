package com.posco.assignoperation.s20g01.domain.vehiclePerformance;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class DeletePerformanceCommand {

    private String registrationId;
}
