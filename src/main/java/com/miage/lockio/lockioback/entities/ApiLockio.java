package com.miage.lockio.lockioback.entities;

import com.miage.lockio.lockioback.enums.LockioSize;
import com.miage.lockio.lockioback.enums.LockioStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiLockio {


    private Long id;


    private Block block;

    private Long localId;


    private LockioSize size;

    private LockioStatus status;

    public ApiLockio(Lockio lockio) {
        this.id = lockio.getId();
        this.block = lockio.getBlock();
        this.localId = lockio.getLocalId();
        this.size = lockio.getSize();
        this.status = lockio.getStatus();
    }

}
