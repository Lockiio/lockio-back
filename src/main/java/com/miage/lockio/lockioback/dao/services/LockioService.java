package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockioService {
    private final LockioRepository lockioRepository;

    public LockioService(LockioRepository lockioRepository) {
        this.lockioRepository = lockioRepository;
    }

    /**
     * Add a List of Lockio to the database
     * @param lockio
     */
    public void addLockios(List<Lockio> lockio) {
        this.lockioRepository.saveAll(lockio);
    }

    /**
     * Update status of a lockio with its global id
     * @param id
     * @param status
     */
    public void updateStatusLockio(Long id, LockioStatus status){
        Lockio lockio = this.lockioRepository.findById(id).orElseThrow();
        lockio.setStatus(status);
        this.lockioRepository.save(lockio);
    }
}


