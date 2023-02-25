package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockio/1/blocks")
public class BlockController {

    private final LockioService lockioService;
    private final LockioRepository lockioRepository;
    private final BlockRepository blockRepository;
    private final RaspberryService raspberryService;

    public BlockController(BlockRepository blockRepository, LockioRepository lockioRepository, LockioService lockioService, RaspberryService raspberryService) {
        this.blockRepository = blockRepository;
        this.lockioRepository = lockioRepository;
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }

    @GetMapping("")
    public List<Block> getBlocks() {
        return this.blockRepository.findAll();
    }

    @GetMapping("/{block-id}")
    public Block getBlocks(@PathVariable ("block-id") Long id) {
        return this.blockRepository.findById(id).get();
    }

    @GetMapping("/{block-id}/lockios")
    public List<Lockio> getAllLockios(@PathVariable("block-id") Long id) {
        // Call to raspberry to update lockios
        List<Lockio> lockios = this.raspberryService.getLockios();
        for (Lockio lockio : lockios) {
            this.lockioService.updateStatusLockio(lockio.getId(), lockio.getStatus());
        }
        return this.lockioRepository.findAllByBlockId(id);
    }

    @GetMapping("/{block-id}/lockios/{local-lockio-id}")
    public Lockio getLockio(@PathVariable("block-id") Long blockId, @PathVariable("local-lockio-id") Long localId) {
        Lockio lockio = this.raspberryService.getLockio(localId);
        this.lockioService.updateStatusLockio(lockio.getId(), lockio.getStatus());
        return this.lockioRepository.findByBlockIdAndLocalId(blockId, localId);
    }

    @PatchMapping("/{block-id}/lockios/{local-lockio-id}")
    public Lockio patchLockio(@PathVariable("block-id") Long blockId, @PathVariable("local-lockio-id") Long localId, @RequestBody String action) {
        Lockio lockio = this.lockioRepository.findByBlockIdAndLocalId(blockId, localId);
        Long global_id = lockio.getId();
        LockioStatus lockioStatus = this.raspberryService.updateStatus(global_id, action);
        this.lockioService.updateStatusLockio(global_id, lockioStatus);
        return lockio;
    }
}
