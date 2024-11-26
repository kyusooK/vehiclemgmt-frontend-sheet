package com.posco.standardmanagement.s20g01.domain.vehicle;

import com.posco.standardmanagement.s20g01.domain.vehicle.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "vehicles", path = "vehicles")
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {}
