package com.posco.assignoperation.s20g01.domain.vehiclePerformance;

import com.posco.assignoperation.s20g01.domain.vehiclePerformance.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "vehiclePerformances",
    path = "vehiclePerformances"
)
public interface VehiclePerformanceRepository
    extends JpaRepository<VehiclePerformance, String> {}
