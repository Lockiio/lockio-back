package com.miage.lockio.lockioback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Coordinates  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String latitude;
    private String longitude;
}
