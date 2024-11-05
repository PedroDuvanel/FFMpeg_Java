package com.clipicate.server.classes;

import java.sql.Timestamp;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private Timestamp creation_date;

    public Gif(int i, String name, String path, Timestamp creation_date) {
        this.name = name;
        this.path = path;
        this.creation_date = creation_date;
    }

}
