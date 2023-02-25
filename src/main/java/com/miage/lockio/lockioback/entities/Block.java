package com.miage.lockio.lockioback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

   private Point coordinate;

    @Enumerated(EnumType.STRING)
    private BlockStatus status;

    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @OneToMany(mappedBy = "block")
    @JsonIgnore
    private List<Lockio> lockio;
}
