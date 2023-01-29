package com.miage.lockio.lockioback.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "lockio")
public class Lockio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String zone;

    @OneToMany()
    private List<Locker> lockers;
}
