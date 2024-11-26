package com.posco.standardmanagement.s20g01.domain.vehicle;

import com.posco.standardmanagement.s20g01.domain.vehicle.DeleteVehicleCommand;
import com.posco.standardmanagement.s20g01.domain.vehicle.RegisterVehicleCommand;
import com.posco.standardmanagement.s20g01.domain.vehicle.UpdateVehicleDetailsCommand;
import com.posco.standardmanagement.s20g01.domain.vehicle.UpdateVehicleStatusCommand;
import com.posco.standardmanagement.s20g01.domain.vehicle.Vehicle;
import com.posco.standardmanagement.s20g01.domain.vehicle.VehicleRepository;
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
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(RegisterVehicleCommand command) {
        Vehicle vehicle = new Vehicle();
        vehicle.setRegistrationNumber(command.getRegistrationNumber());
        vehicle.setMake(command.getMake());
        vehicle.setModel(command.getModel());
        vehicle.setYear(command.getYear());
        vehicle.setStatus(command.getStatus());
        return vehicleRepository.save(vehicle);
    }

    public Vehicle update(Long id, UpdateVehicleDetailsCommand command) {
        Vehicle existing = findById(id);
        if (command.getId() != null) {
            existing.setId(command.getId());
        }
        if (command.getRegistrationNumber() != null) {
            existing.setRegistrationNumber(command.getRegistrationNumber());
        }
        if (command.getMake() != null) {
            existing.setMake(command.getMake());
        }
        if (command.getModel() != null) {
            existing.setModel(command.getModel());
        }
        if (command.getYear() != null) {
            existing.setYear(command.getYear());
        }
        return vehicleRepository.save(existing);
    }

    public void delete(Long id) {
        Vehicle vehicle = findById(id);
        vehicleRepository.delete(vehicle);
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findById(Long id) {
        return vehicleRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vehicle not found"
                )
            );
    }
    //// readModel mybatis
}
