package com.miage.lockio.lockioback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miage.lockio.lockioback.enums.LockioSize;
import com.miage.lockio.lockioback.enums.LockioStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name="lockio", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"block_id","local_id"})
})
public class Lockio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn (name="block_id")
    @JsonIgnore
    private Block block;

    //TODO : create a sequence to increment the local_id properly
    @Column(name = "local_id")
    private Long localId;

    @Enumerated(EnumType.STRING)
    private LockioSize size;

    @Enumerated(EnumType.STRING)
    private LockioStatus status;
}
