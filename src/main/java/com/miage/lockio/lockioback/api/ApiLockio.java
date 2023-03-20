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
    private Long localId;
    private LockioSize size;
    private LockioStatus status;
    private int redGPIOPIn;
    private int greenGPIOPIn;
    private Long blockId;

    public ApiLockio(Lockio lockio) {
        this.id = lockio.getId();
        this.localId = lockio.getLocalId();
        this.size = lockio.getSize();
        this.status = lockio.getStatus();
        this.redGPIOPIn = lockio.getRedGPIOPin();
        this.greenGPIOPIn = lockio.getGreenGPIOPin();
        this.blockId = lockio.getBlock().getId();

    }

}
