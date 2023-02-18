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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"block_id","local_id"})
})
public class Lockio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO ADD ANNOTATION TO LINK LOCKIO WITH BLOCK
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @Column(name="block_id")
    private Block block;

    //TODO ADD ANNOTATION TO LINK AND AUTO INCREMENT LOCAL ID
    @Column(name = "local_id")
    private long localId;

    @Enumerated(EnumType.STRING)
    private LockioSize size;

    @Enumerated(EnumType.STRING)
    private LockioStatus status;

}
