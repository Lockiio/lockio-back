package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.dao.repositories.LockioCodeRepository;
import com.miage.lockio.lockioback.entities.LockioCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LockioCodeService {

    private final LockioCodeRepository lockioCodeRepository;

    public void addLockioCode(LockioCode lockioCode) {
        this.lockioCodeRepository.save(lockioCode);
    }

    public String updateLockioCode(LockioCode lockioCode) {
        this.generateLockioCode(lockioCode);
        return lockioCode.getCode();
    }

    public void generateLockioCode(LockioCode lockioCode) {
        String code = String.format("%04d", (int) (Math.random() * 10000));
        lockioCode.setCode(code);
        this.lockioCodeRepository.save(lockioCode);
    }

    public boolean resetLockioCode(LockioCode lockioCode, String code) {
        if (lockioCode.getCode().equals(code)) {
            lockioCode.setCode(null);
            this.lockioCodeRepository.save(lockioCode);
            return true;
        } else {
            return false;
        }
    }
}
