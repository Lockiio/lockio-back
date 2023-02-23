package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.stereotype.Service;

@Service
public class LockioService {
    private final LockioRepository lockioRepository;

    public LockioService(LockioRepository lockioRepository) {
        this.lockioRepository = lockioRepository;
    }

    public void addLockio(Lockio lockio) {
        this.lockioRepository.save(lockio);
    }
    public void updateStatusLockio(Long id, LockioStatus status){ Lockio lockio =this.lockioRepository.findById(id).get();
        lockio.setStatus(status);
        this.lockioRepository.save(lockio);
    }

}


