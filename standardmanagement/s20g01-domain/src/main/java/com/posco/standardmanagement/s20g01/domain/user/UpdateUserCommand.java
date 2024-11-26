package com.posco.standardmanagement.s20g01.domain.user;

import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class UpdateUserCommand {

    private Long id;
    private String name;
    private String email;
}
