package com.miage.lockio.lockioback.services;

import com.miage.lockio.lockioback.entities.Locker;
import com.miage.lockio.lockioback.repositories.LockerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LockerService {
    private LockerRepository lockerRepository;

    public LockerService(LockerRepository lockerRepository) {
        this.lockerRepository = lockerRepository;
    }

    public List<Locker> getAllLockers() {
        return this.lockerRepository.findAll();
    }
}
