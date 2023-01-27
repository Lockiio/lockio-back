package com.miage.lockio.lockioback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Locker {

    @Id
    private long id;

    private boolean available;

    /**
     * Size of the locker
     * Can be 1,2 or 3
     */
    //todo change int by enum ? (little, medium, large)
    private int size;
}
