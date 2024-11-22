package com.clipicate.server.classes;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

@EqualsAndHashCode
@Entity
@Data
@Table(name = "gifs")
public class Gif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String description;

    @Column(name = "file64", columnDefinition = "BYTEA")
    private byte[] file64;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp creation_date;
}
