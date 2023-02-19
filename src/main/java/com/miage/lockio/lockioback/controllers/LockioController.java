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
@RequestMapping("api/lockio/v1/blocks")
public class LockioController {

    private final LockioService lockioService;
    private final RaspberryService raspberryService;

    public LockioController(LockioService lockioService, RaspberryService raspberryService) {
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }


    @GetMapping("/1/lockios/{lockio-id}")
    public Optional<Lockio> getLockio( @PathVariable("lockio-id") long id_lockio ) {
        return this.lockioService.getLockio(id_lockio);
    }




    @PatchMapping("/1/lockios/{lockio-id}")
    public Lockio updateLockio(@PathVariable ("lockio-id") Long id){
        LockioStatus status=LockioStatus.valueOf(raspberryService.updateStatusLockio(id));
        this.lockioService.updateStatusLockio(id,status);
        return lockioService.getLockio(id).get();

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
