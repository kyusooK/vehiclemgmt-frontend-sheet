package com.posco.assignoperation.s20g01.domain.carAssignment;

import com.posco.assignoperation.s20g01.domain.carAssignment.CancelCarAssignmentCommand;
import com.posco.assignoperation.s20g01.domain.carAssignment.CarAssignment;
import com.posco.assignoperation.s20g01.domain.carAssignment.CarAssignmentRepository;
import com.posco.assignoperation.s20g01.domain.carAssignment.RequestCarAssignmentCommand;
import com.posco.assignoperation.s20g01.domain.carAssignment.UpdateCarAssignmentCommand;
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
public class CarAssignmentService {

    private final CarAssignmentRepository carAssignmentRepository;

    @Autowired
    public CarAssignmentService(
        CarAssignmentRepository carAssignmentRepository
    ) {
        this.carAssignmentRepository = carAssignmentRepository;
    }

    public CarAssignment create(RequestCarAssignmentCommand command) {
        CarAssignment carAssignment = new CarAssignment();
        carAssignment.setId(command.getId());
        carAssignment.setRequesterName(command.getRequesterName());
        carAssignment.setOrganization(command.getOrganization());
        carAssignment.setEmployeeNumber(command.getEmployeeNumber());
        carAssignment.setOfficeNumber(command.getOfficeNumber());
        carAssignment.setMobileNumber(command.getMobileNumber());
        carAssignment.setRequestDate(command.getRequestDate());
        carAssignment.setApproverInfo(command.getApproverInfo());
        carAssignment.setApproverPosition(command.getApproverPosition());
        carAssignment.setUsagePurpose(command.getUsagePurpose());
        carAssignment.setNumberOfPassengers(command.getNumberOfPassengers());
        carAssignment.setRouteSetting(command.getRouteSetting());
        carAssignment.setRemarks(command.getRemarks());
        carAssignment.setPassengerContact(command.getPassengerContact());
        carAssignment.setAttachedDocuments(command.getAttachedDocuments());
        carAssignment.setCancellationReason(command.getCancellationReason());
        carAssignment.setUsageCategory(command.getUsageCategory());
        carAssignment.setCarType(command.getCarType());
        carAssignment.setMainDepartment(command.getMainDepartment());
        carAssignment.setOperationSection(command.getOperationSection());
        carAssignment.setOperationType(command.getOperationType());
        carAssignment.setIncludeDriver(command.getIncludeDriver());
        carAssignment.setProgressStage(command.getProgressStage());
        carAssignment.setPeriod(command.getPeriod());
        return carAssignmentRepository.save(carAssignment);
    }

    public void delete(Long id) {
        CarAssignment carAssignment = findById(id);
        carAssignmentRepository.delete(carAssignment);
    }

    public CarAssignment update(Long id, UpdateCarAssignmentCommand command) {
        CarAssignment existing = findById(id);
        if (command.getId() != null) {
            existing.setId(command.getId());
        }
        if (command.getRequesterName() != null) {
            existing.setRequesterName(command.getRequesterName());
        }
        if (command.getOrganization() != null) {
            existing.setOrganization(command.getOrganization());
        }
        if (command.getEmployeeNumber() != null) {
            existing.setEmployeeNumber(command.getEmployeeNumber());
        }
        if (command.getOfficeNumber() != null) {
            existing.setOfficeNumber(command.getOfficeNumber());
        }
        if (command.getMobileNumber() != null) {
            existing.setMobileNumber(command.getMobileNumber());
        }
        if (command.getRequestDate() != null) {
            existing.setRequestDate(command.getRequestDate());
        }
        if (command.getApproverInfo() != null) {
            existing.setApproverInfo(command.getApproverInfo());
        }
        if (command.getApproverPosition() != null) {
            existing.setApproverPosition(command.getApproverPosition());
        }
        if (command.getUsagePurpose() != null) {
            existing.setUsagePurpose(command.getUsagePurpose());
        }
        if (command.getNumberOfPassengers() != null) {
            existing.setNumberOfPassengers(command.getNumberOfPassengers());
        }
        if (command.getRouteSetting() != null) {
            existing.setRouteSetting(command.getRouteSetting());
        }
        if (command.getRemarks() != null) {
            existing.setRemarks(command.getRemarks());
        }
        if (command.getPassengerContact() != null) {
            existing.setPassengerContact(command.getPassengerContact());
        }
        if (command.getAttachedDocuments() != null) {
            existing.setAttachedDocuments(command.getAttachedDocuments());
        }
        if (command.getCancellationReason() != null) {
            existing.setCancellationReason(command.getCancellationReason());
        }
        if (command.getUsageCategory() != null) {
            existing.setUsageCategory(command.getUsageCategory());
        }
        if (command.getCarType() != null) {
            existing.setCarType(command.getCarType());
        }
        if (command.getMainDepartment() != null) {
            existing.setMainDepartment(command.getMainDepartment());
        }
        if (command.getOperationSection() != null) {
            existing.setOperationSection(command.getOperationSection());
        }
        if (command.getOperationType() != null) {
            existing.setOperationType(command.getOperationType());
        }
        if (command.getIncludeDriver() != null) {
            existing.setIncludeDriver(command.getIncludeDriver());
        }
        if (command.getProgressStage() != null) {
            existing.setProgressStage(command.getProgressStage());
        }
        if (command.getPeriod() != null) {
            existing.setPeriod(command.getPeriod());
        }
        return carAssignmentRepository.save(existing);
    }

    public CarAssignment save(CarAssignment carAssignment) {
        return carAssignmentRepository.save(carAssignment);
    }

    public List<CarAssignment> findAll() {
        return carAssignmentRepository.findAll();
    }

    public CarAssignment findById(Long id) {
        return carAssignmentRepository
            .findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "CarAssignment not found"
                )
            );
    }
    //// readModel mybatis
}
