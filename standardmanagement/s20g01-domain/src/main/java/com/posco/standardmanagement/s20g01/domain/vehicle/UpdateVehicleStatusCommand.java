package com.posco.standardmanagement.s20g01.domain.vehicle;

import com.posco.standardmanagement.s20g01.domain.VehicleStatus;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateVehicleStatusCommand {

    private Long id;
    private VehicleStatus newStatus;
}
