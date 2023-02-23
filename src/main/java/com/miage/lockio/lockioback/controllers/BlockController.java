package com.miage.lockio.lockioback.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockio/1")
public class BlockController {

    private final LockioService lockioService;

    private final BlockRepository blockRepository;
    private final RaspberryService raspberryService;

    public BlockController(BlockRepository blockRepository, LockioService lockioService, RaspberryService raspberryService) {
        this.blockRepository = blockRepository;
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }

    @GetMapping("/blocks")
    public List<Block> getBlocks() {
        return this.blockRepository.findAll();
    }

    @GetMapping("/blocks/{block-id}")
    public Block getBlocks(@PathVariable ("block-id") long id) {
        return this.blockRepository.getReferenceById(id);
    }

    @GetMapping("/blocks/{block-id}/lockios")
    public List<Lockio> getLockios(@PathVariable("block-id") long id) throws JsonProcessingException {
       List<Lockio> lockios = this.raspberryService.getLockios();
       for (Lockio lockio:lockios) {
           this.lockioService.updateStatusLockio(lockio.getId(),lockio.getStatus());
       }
        return this.blockRepository.getReferenceById(id).getLockio();
    }

    @GetMapping("/blocks/{block-id}/lockios/{local-lockio-id}")
    public Lockio getLockios(@PathVariable("block-id") long id_block, @PathVariable("local-lockio-id") int idLocal ) {
        return this.blockRepository.getReferenceById(id_block).getLockio().get(idLocal);
    }

    @PatchMapping("/blocks/{block-id}/lockios/{local-id}")
    public Lockio patchLockio(@PathVariable("block-id") long id_block, @PathVariable ("local-id") int id_local, @RequestBody String action ){
        Lockio lockio = this.blockRepository.getReferenceById(id_block).getLockio().get(id_local);
        long global_id = lockio.getId();
        LockioStatus lockioStatus = this.raspberryService.updateStatus(global_id,action);
        this.lockioService.updateStatusLockio(global_id,lockioStatus);
        return lockio;
    }


}
