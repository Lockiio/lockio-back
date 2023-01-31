package com.miage.lockio.lockioback.services;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.repositories.LockioRepository;
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
}
