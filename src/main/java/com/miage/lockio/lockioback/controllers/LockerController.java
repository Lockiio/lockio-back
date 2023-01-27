package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Locker;
import com.miage.lockio.lockioback.services.LockerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

@RestController
@RequestMapping("/api/lockers")
public class LockerController {

    private LockerService lockerService;

    public LockerController(LockerService lockerService) {
        this.lockerService = lockerService;
    }

    @GetMapping()
    public List<Locker> getLockers() {
        Locker l = new Locker();
//        return Arrays.asList(l);
        return this.lockerService.getAllLockers();
    }
}
