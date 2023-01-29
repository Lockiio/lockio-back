package com.miage.lockio.lockioback.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Table(name = "locker")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean available;

    /**
     * Size of the locker
     * Can be 1,2 or 3
     */
    //todo change int by enum ? (little, medium, large)
    private int size;
}
