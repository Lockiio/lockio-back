package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

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

    private String longitude;

    private String latitude;

    private String zone;

    @Enumerated(EnumType.STRING)
    private BlockStatus status;

    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @OneToMany(mappedBy = "block")
    private Collection<Lockio> lockio;
}
