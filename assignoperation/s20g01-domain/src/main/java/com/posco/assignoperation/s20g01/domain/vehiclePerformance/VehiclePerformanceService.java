package com.posco.assignoperation.s20g01.domain.vehiclePerformance;

import com.posco.assignoperation.s20g01.domain.vehiclePerformance.DeletePerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.ModifyPerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.RegisterDrivingLogCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.RegisterPerformanceCommand;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformance;
import com.posco.assignoperation.s20g01.domain.vehiclePerformance.VehiclePerformanceRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class VehiclePerformanceService {

    private final VehiclePerformanceRepository vehiclePerformanceRepository;

    @Autowired
    public VehiclePerformanceService(
        VehiclePerformanceRepository vehiclePerformanceRepository
    ) {
        this.vehiclePerformanceRepository = vehiclePerformanceRepository;
    }

    public VehiclePerformance create(RegisterPerformanceCommand command) {
        VehiclePerformance vehiclePerformance = new VehiclePerformance();
        vehiclePerformance.setRegistrationId(command.getRegistrationId());
        vehiclePerformance.setVehicleNumber(command.getVehicleNumber());
        vehiclePerformance.setRegistrationDate(command.getRegistrationDate());
        vehiclePerformance.setDeparture(command.getDeparture());
        vehiclePerformance.setDepartureTime(command.getDepartureTime());
        vehiclePerformance.setAccumulatedDistanceBefore(
            command.getAccumulatedDistanceBefore()
        );
        vehiclePerformance.setDestination(command.getDestination());
        vehiclePerformance.setArrivalTime(command.getArrivalTime());
        vehiclePerformance.setAccumulatedDistanceAfter(
            command.getAccumulatedDistanceAfter()
        );
        vehiclePerformance.setDrivingDistance(command.getDrivingDistance());
        vehiclePerformance.setPurpose(command.getPurpose());
        vehiclePerformance.setPeriod(command.getPeriod());
        return vehiclePerformanceRepository.save(vehiclePerformance);
    }

    public VehiclePerformance update(
        String id,
        ModifyPerformanceCommand command
    ) {
        VehiclePerformance existing = findById(id);
        if (command.getRegistrationId() != null) {
            existing.setRegistrationId(command.getRegistrationId());
        }
        if (command.getVehicleNumber() != null) {
            existing.setVehicleNumber(command.getVehicleNumber());
        }
        if (command.getRegistrationDate() != null) {
            existing.setRegistrationDate(command.getRegistrationDate());
        }
        if (command.getDeparture() != null) {
            existing.setDeparture(command.getDeparture());
        }
        if (command.getDepartureTime() != null) {
            existing.setDepartureTime(command.getDepartureTime());
        }
        if (command.getAccumulatedDistanceBefore() != null) {
            existing.setAccumulatedDistanceBefore(
                command.getAccumulatedDistanceBefore()
            );
        }
        if (command.getDestination() != null) {
            existing.setDestination(command.getDestination());
        }
        if (command.getArrivalTime() != null) {
            existing.setArrivalTime(command.getArrivalTime());
        }
        if (command.getAccumulatedDistanceAfter() != null) {
            existing.setAccumulatedDistanceAfter(
                command.getAccumulatedDistanceAfter()
            );
        }
        if (command.getDrivingDistance() != null) {
            existing.setDrivingDistance(command.getDrivingDistance());
        }
        if (command.getPurpose() != null) {
            existing.setPurpose(command.getPurpose());
        }
        if (command.getPeriod() != null) {
            existing.setPeriod(command.getPeriod());
        }
        return vehiclePerformanceRepository.save(existing);
    }

    public void delete(String id) {
        VehiclePerformance vehiclePerformance = findById(id);
        vehiclePerformanceRepository.delete(vehiclePerformance);
    }

    public VehiclePerformance save(VehiclePerformance vehiclePerformance) {
        return vehiclePerformanceRepository.save(vehiclePerformance);
    }

    public List<VehiclePerformance> findAll() {
        return vehiclePerformanceRepository.findAll();
    }

    public VehiclePerformance findById(String id) {
        return vehiclePerformanceRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "VehiclePerformance not found"
                )
            );
    }
    //// readModel mybatis
}
