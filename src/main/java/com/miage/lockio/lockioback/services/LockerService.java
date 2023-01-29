package com.miage.lockio.lockioback.services;

import com.miage.lockio.lockioback.entities.Locker;
import com.miage.lockio.lockioback.repositories.LockerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LockerService {
    private final LockerRepository lockerRepository;

    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public List<Locker> getAllLockers() {
        return this.lockerRepository.findAll();
    }

    public Optional<Locker> getLocker(long id) {
        return this.lockerRepository.findById(id);
    }

    public void addLocker(Locker locker) {
        this.lockerRepository.save(locker);
    }
}
