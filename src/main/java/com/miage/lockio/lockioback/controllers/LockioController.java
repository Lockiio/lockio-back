package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.ApiLockio;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lockio/1/lockios")
public class LockioController {

    private final LockioService lockioService;
    private final LockioRepository lockioRepository;
    private final RaspberryService raspberryService;

    @GetMapping("/{lockio-id}")
    public Lockio getLockio(@PathVariable("lockio-id") Long lockio_id) {
        Lockio lockio = this.lockioRepository.findById(lockio_id).orElseThrow();
        Lockio lockioFromRaspberry = this.raspberryService.getLockio(lockio.getLocalId());
        this.lockioService.updateStatusLockio(lockio.getId(), lockioFromRaspberry.getStatus());
        return this.lockioRepository.findById(lockio_id).orElseThrow();
    }

    @PatchMapping("/{lockio-id}")
    //TODO : replace String by LockioStatus for action
    public Lockio patchLockio(@PathVariable ("lockio-id") Long lockio_id, @RequestBody String action) {
        LockioStatus lockioStatus = raspberryService.updateStatus(lockio_id,action);
        this.lockioService.updateStatusLockio(lockio_id,lockioStatus);
        return this.lockioRepository.findById(lockio_id).orElseThrow();
    }


    @GetMapping("/{lockio-id}/apiLockioTest")
    public ApiLockio getLockioAPI(@PathVariable("lockio-id") Long lockio_id) {
        Lockio lockio = this.lockioRepository.findById(lockio_id).orElseThrow();
        Lockio lockioFromRaspberry = this.raspberryService.getLockio(lockio.getLocalId());
        this.lockioService.updateStatusLockio(lockio.getId(), lockioFromRaspberry.getStatus());
        return new ApiLockio(this.lockioRepository.findById(lockio_id).orElseThrow());
    }
}
