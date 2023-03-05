package com.miage.lockio.lockioback.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiStatusCodeLockio {
    private String status;
    private String code;
    public ApiStatusCodeLockio(String status, String code) {
        this.status = status;
        this.code = code;
    }
}
