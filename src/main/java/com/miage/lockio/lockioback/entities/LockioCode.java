package com.miage.lockio.lockioback.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="lockio_code")
@IdClass(RelationshipBlockLockio.class)
public class LockioCode {

    @Id
    @ManyToOne(targetEntity = Block.class)
    private Block block;

    @Id
    @ManyToOne(targetEntity = Lockio.class)
    private Lockio lockio;

    private String code;

    public LockioCode(Lockio lockio, Block block) {
        this.lockio = lockio;
        this.block = block;
    }
}
