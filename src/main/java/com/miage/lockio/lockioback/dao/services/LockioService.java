package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LockioService {
    private final LockioRepository lockioRepository;

    public LockioService(LockioRepository lockioRepository) {
        this.lockioRepository = lockioRepository;
    }

    public List<Lockio> getAllLockios() {
        return this.lockioRepository.findAll();
    }

    public Optional<Lockio> getLockio(long id) {
        return this.lockioRepository.findById(id);
    }

    public void addLockio(Lockio lockio) {

        this.lockioRepository.save(lockio);
    }
    public void updateStatusLockio(Long id, LockioStatus status){ this.lockioRepository.findById(id).get().setStatus(status);}
    public Lockio save(Lockio lockio) {
        return lockioRepository.save(lockio);
    }


}


