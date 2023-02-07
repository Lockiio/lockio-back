package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.services.LockioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockio/1/")
public class LockioController {

    private final LockioService lockioService;

    public LockioController(LockioService lockioService) {
        this.lockioService = lockioService;
    }

    @GetMapping()
    public List<Lockio> getLockios() {
        return this.lockioService.getAllLockios();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Lockio> addLockio(@RequestBody Lockio lockio) {
        this.lockioService.addLockio(lockio);
        if (lockio.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(lockio);
    }
}
