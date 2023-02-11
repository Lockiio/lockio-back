package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.dao.services.LockioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/lockio/1/")
public class LockioController {

    private final LockioService lockioService;
    private static final String RASP_PATH = "http://localhost:5000/";

    public LockioController(LockioService lockioService) {
        this.lockioService = lockioService;
    }

    @GetMapping()
    public List<Lockio> getLockios() {
        return this.lockioService.getAllLockios();
    }

    @GetMapping("/status")
    public String getstatusLockios() {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(RASP_PATH+"status", String.class);


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
