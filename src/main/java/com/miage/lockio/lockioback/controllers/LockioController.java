package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lockio")
public class LockioController {

    private final LockioService lockioService;
    private final RaspberryService raspberryService;

    public LockioController(LockioService lockioService, RaspberryService raspberryService) {
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }

    @GetMapping()
    public List<Lockio> getLockios() {
        return this.lockioService.getAllLockios();
    }

    @GetMapping("/{id}")
    public Optional<Lockio> getLockio(@PathVariable long id ) {
        return this.lockioService.getLockio(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Lockio> updateLockio(@PathVariable Long id){
        LockioStatus status=LockioStatus.valueOf(raspberryService.getStatusLockio(id));
        this.lockioService.updateStatusLockio(id,status);
        return ResponseEntity.ok(lockioService.getLockio(id).get());

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
