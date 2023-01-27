package com.miage.lockio.lockioback.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Lockio {

    @Id
    private long id;

    private String zone;

    @OneToMany()
    @JoinColumn(name="id")
    private List<Locker> lockers;
}
