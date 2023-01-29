package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Locker;
import com.miage.lockio.lockioback.services.LockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private final LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping()
    public List<Locker> getLockers() {
        return this.lockerService.getAllLockers();
    }

    @PostMapping()
    public void addLocker(Locker locker) {
        this.lockerService.addLocker(locker);
    }
}
