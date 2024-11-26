package com.posco.assignoperation.s20g01.service;

import com.posco.assignoperation.s20g01.domain.carAssignment.CancelCarAssignmentCommand;
import com.posco.assignoperation.s20g01.domain.carAssignment.CarAssignment;
import com.posco.assignoperation.s20g01.domain.carAssignment.CarAssignmentService;
import com.posco.assignoperation.s20g01.domain.carAssignment.RequestCarAssignmentCommand;
import com.posco.assignoperation.s20g01.domain.carAssignment.UpdateCarAssignmentCommand;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class CarAssignmentController {

    private final CarAssignmentService carAssignmentService;

    @Autowired
    public CarAssignmentController(CarAssignmentService carAssignmentService) {
        this.carAssignmentService = carAssignmentService;
    }

    @GetMapping(path = "/carAssignments")
    public ResponseEntity<List<CarAssignment>> findAll() {
        return ResponseEntity.ok(carAssignmentService.findAll());
    }

    @PostMapping(path = "/carAssignments")
    public ResponseEntity<CarAssignment> create(
        @Valid @RequestBody RequestCarAssignmentCommand command
    ) {
        return ResponseEntity.ok(carAssignmentService.create(command));
    }

    @DeleteMapping(path = "/carAssignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/carAssignments/{id}")
    public ResponseEntity<CarAssignment> update(
        @PathVariable Long id,
        @Valid @RequestBody UpdateCarAssignmentCommand command
    ) {
        return ResponseEntity.ok(carAssignmentService.update(id, command));
    }
    //readModel rest api
}
