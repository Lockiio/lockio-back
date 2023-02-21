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
@RequestMapping("api/lockio/1")
public class LockioController {

    private final LockioService lockioService;
    private final RaspberryService raspberryService;

    public LockioController(LockioService lockioService, RaspberryService raspberryService) {
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }


    @GetMapping("/lockios/{lockio-id}")
    public Lockio getLockio( @PathVariable("lockio-id") long id_lockio ) {
        Lockio lockio= raspberryService.getLockio(id_lockio);
        this.lockioService.updateStatusLockio(id_lockio,lockio.getStatus());
        return this.lockioService.getLockio(id_lockio).get();
    }



    @PatchMapping ("/lockios/{lockio-id}")
    public Lockio patchLockio(@PathVariable ("lockio-id") Long id, @RequestBody String action ){


        LockioStatus lockioStatus=raspberryService.updateStatus(id,action);
        this.lockioService.updateStatusLockio(id,lockioStatus);
        return lockioService.getLockio(id).get();
    }

    @PatchMapping("/lockios/{lockio-id}/test")
    public Lockio updateLockio(@PathVariable ("lockio-id") Long id){
        LockioStatus status=LockioStatus.valueOf(raspberryService.updateStatusLockio(id));
        this.lockioService.updateStatusLockio(id,status);
        return lockioService.getLockio(id).get();

    }



}
