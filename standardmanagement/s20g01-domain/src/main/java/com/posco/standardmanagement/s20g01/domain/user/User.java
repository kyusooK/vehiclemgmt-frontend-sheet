package com.posco.standardmanagement.s20g01.domain.user;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "User_table")
@Data
public class User {

    @Id
    private Long id;

    private String name;

    private String email;
}
