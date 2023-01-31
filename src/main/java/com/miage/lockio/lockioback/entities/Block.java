package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import jakarta.persistence.*;

@Entity
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    //private Coordinates coordinates;

    private String zone;

    private BlockStatus status;

    private Privacy privacy;
}
