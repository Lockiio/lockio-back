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
}
