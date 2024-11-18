package com.clipicate.server.classes;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@EqualsAndHashCode
@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private Timestamp creation_date;
}
