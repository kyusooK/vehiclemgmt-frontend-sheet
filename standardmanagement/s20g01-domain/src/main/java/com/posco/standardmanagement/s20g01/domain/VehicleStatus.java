package com.posco.standardmanagement.s20g01.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

public enum VehicleStatus {
    AVAILABLE,
    IN_USE,
    UNDER_MAINTENANCE,
    DECOMMISSIONED,
}
