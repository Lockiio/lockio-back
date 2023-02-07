package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    private String zone;

    @Enumerated(EnumType.STRING)
    private BlockStatus status;

    @Enumerated(EnumType.STRING)
    private Privacy privacy;
}
