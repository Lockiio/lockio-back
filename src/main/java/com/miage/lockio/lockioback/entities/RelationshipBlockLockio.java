package com.miage.lockio.lockioback.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipBlockLockio implements Serializable {

    private Block block;

    private Lockio lockio;
}
