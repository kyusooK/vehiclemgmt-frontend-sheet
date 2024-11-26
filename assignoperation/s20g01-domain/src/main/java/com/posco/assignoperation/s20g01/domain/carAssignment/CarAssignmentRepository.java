package com.posco.assignoperation.s20g01.domain.carAssignment;

import com.posco.assignoperation.s20g01.domain.carAssignment.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(
    collectionResourceRel = "carAssignments",
    path = "carAssignments"
)
public interface CarAssignmentRepository
    extends JpaRepository<CarAssignment, Long> {}
