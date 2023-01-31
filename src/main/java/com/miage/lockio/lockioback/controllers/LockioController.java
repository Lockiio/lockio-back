package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.services.LockioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lockios")
public class LockioController {

    private final LockioService lockioService;

    public LockioController(LockioService lockioService) {
        this.lockioService = lockioService;
    }

    @GetMapping()
    public List<Lockio> getLockios() {
        return this.lockioService.getAllLockios();
    }

    @PostMapping()
    public void addLockio(Lockio lockio) {
        this.lockioService.addLockio(lockio);
        //TODO NEED TO RETURN THE ID of the created lockio
    }
}
