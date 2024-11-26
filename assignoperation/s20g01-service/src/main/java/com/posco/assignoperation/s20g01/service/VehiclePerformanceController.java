package com.posco.assignoperation.s20g01.service;

import com.posco.assignoperation.s20g01.domain.vehiclePerformance.DeletePerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.ModifyPerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.RegisterDrivingLogCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.RegisterPerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformance;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformanceService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
public class VehiclePerformanceController {

    private final VehiclePerformanceService vehiclePerformanceService;

    @Autowired
    public VehiclePerformanceController(
        VehiclePerformanceService vehiclePerformanceService
    ) {
        this.vehiclePerformanceService = vehiclePerformanceService;
    }

    @GetMapping(path = "/vehiclePerformances")
    public ResponseEntity<List<VehiclePerformance>> findAll() {
        return ResponseEntity.ok(vehiclePerformanceService.findAll());
    }

    @PostMapping(path = "/vehiclePerformances")
    public ResponseEntity<VehiclePerformance> create(
        @Valid @RequestBody RegisterPerformanceCommand command
    ) {
        return ResponseEntity.ok(vehiclePerformanceService.create(command));
    }

    @PatchMapping(path = "/vehiclePerformances/{registrationId}")
    public ResponseEntity<VehiclePerformance> update(
        @PathVariable String registrationId,
        @Valid @RequestBody ModifyPerformanceCommand command
    ) {
        return ResponseEntity.ok(
            vehiclePerformanceService.update(registrationId, command)
        );
    }

    @DeleteMapping(path = "/vehiclePerformances/{registrationId}")
    public ResponseEntity<Void> delete(@PathVariable String registrationId) {
        vehiclePerformanceService.delete(registrationId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
        path = "/vehiclePerformances/registerDrivingLog/{registrationId}"
    )
    public ResponseEntity<VehiclePerformance> registerDrivingLog(
        @PathVariable("registrationId") String registrationId,
        @Valid @RequestBody RegisterDrivingLogCommand command
    ) {
        VehiclePerformance vehiclePerformance = vehiclePerformanceService.findById(
            registrationId
        );

        // 도메인 포트 메서드 직접 호출
        vehiclePerformance.registerDrivingLog(command);

        return ResponseEntity.ok(
            vehiclePerformanceService.save(vehiclePerformance)
        );
    }
    //readModel rest api
}
