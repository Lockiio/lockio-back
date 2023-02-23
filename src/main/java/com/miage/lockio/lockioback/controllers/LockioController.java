package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/lockio/1")
public class LockioController {

    private final LockioService lockioService;
    private final LockioRepository lockioRepository;
    private final RaspberryService raspberryService;

    public LockioController(LockioService lockioService, LockioRepository lockioRepository, RaspberryService raspberryService) {
        this.lockioService = lockioService;
        this.lockioRepository = lockioRepository;
        this.raspberryService = raspberryService;
    }

    @GetMapping("/lockios/{lockio-id}")
    public Lockio getLockio( @PathVariable("lockio-id") long lockio_id ) {
        Lockio lockio= raspberryService.getLockio(lockio_id);
        this.lockioService.updateStatusLockio(lockio_id,lockio.getStatus());
        return this.lockioRepository.getReferenceById(lockio_id);
    }

    @PatchMapping ("/lockios/{lockio-id}")
    public Lockio patchLockio(@PathVariable ("lockio-id") Long lockio_id, @RequestBody String action ){
        LockioStatus lockioStatus=raspberryService.updateStatus(lockio_id,action);
        this.lockioService.updateStatusLockio(lockio_id,lockioStatus);
        return this.lockioRepository.getReferenceById(lockio_id);
    }
}
