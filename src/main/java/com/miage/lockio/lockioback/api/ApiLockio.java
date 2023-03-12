package com.miage.lockio.lockioback.api;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioSize;
import com.miage.lockio.lockioback.enums.LockioStatus;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ApiLockio {
    private Long id;
    private Long blockId;
    private Long localId;
    private LockioSize size;
    private LockioStatus status;
    public ApiLockio(Lockio lockio) {
        this.id = lockio.getId();
        this.blockId = lockio.getBlock().getId();
        this.localId = lockio.getLocalId();
        this.size = lockio.getSize();
        this.status = lockio.getStatus();
    }

}
