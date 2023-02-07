package com.miage.lockio.lockioback.entities;

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
@Table(name = "lockio")
public class Lockio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO ADD ANNOTATION TO LINK LOCKIO WITH BLOCK
    private long blockId;

    //TODO ADD ANNOTATION TO LINK
    private long localId;

    private LockioSize size;

    private LockioStatus status;
}
