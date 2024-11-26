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
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CarAssignment_table")
@Data
public class CarAssignment {

    @Id
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

    @Enumerated(EnumType.STRING)
    private UsageCategory usageCategory;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @Enumerated(EnumType.STRING)
    private MainDepartment mainDepartment;

    @Enumerated(EnumType.STRING)
    private OperationSection operationSection;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    private IncludeDriver includeDriver;

    @Enumerated(EnumType.STRING)
    private ProgressStage progressStage;

    @Embedded
    private Period period;
}
