package com.posco.assignoperation.s20g01.domain.carAssignment;

import com.posco.assignoperation.s20g01.domain.CarType;
import com.posco.assignoperation.s20g01.domain.IncludeDriver;
import com.posco.assignoperation.s20g01.domain.MainDepartment;
import com.posco.assignoperation.s20g01.domain.OperationSection;
import com.posco.assignoperation.s20g01.domain.OperationType;
import com.posco.assignoperation.s20g01.domain.Period;
import com.posco.assignoperation.s20g01.domain.ProgressStage;
import com.posco.assignoperation.s20g01.domain.UsageCategory;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateCarAssignmentCommand {

    private Long id;
    private String requesterName;
    private String organization;
    private String employeeNumber;
    private String officeNumber;
    private String mobileNumber;
    private Date requestDate;
    private String approverInfo;
    private String approverPosition;
    private String usagePurpose;
    private String numberOfPassengers;
    private String routeSetting;
    private String remarks;
    private String passengerContact;
    private String attachedDocuments;
    private String cancellationReason;
    private UsageCategory usageCategory;
    private CarType carType;
    private MainDepartment mainDepartment;
    private OperationSection operationSection;
    private OperationType operationType;
    private IncludeDriver includeDriver;
    private ProgressStage progressStage;
    private Period period;
}
