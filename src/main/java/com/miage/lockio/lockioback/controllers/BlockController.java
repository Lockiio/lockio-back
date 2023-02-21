package com.miage.lockio.lockioback.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.dao.services.BlockService;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lockio/1")
public class BlockController {

    private final BlockService blockService;
    private final LockioService lockioService;
    private final RaspberryService raspberryService;

    public BlockController(BlockService blockService, LockioService lockioService, RaspberryService raspberryService) {
        this.blockService = blockService;
        this.lockioService = lockioService;
        this.raspberryService = raspberryService;
    }

    @GetMapping("/blocks")
    public List<Block> getBlocks() {
        return this.blockService.getAllBlocks();
    }

    @GetMapping("/blocks/{block-id}")
    public Block getBlocks(@PathVariable ("block-id") long id) {
        return this.blockService.getBlock(id).get();
    }

    @GetMapping("/blocks/{block-id}/lockios")
    public List<Lockio> getLockios(@PathVariable("block-id") long id) throws JsonProcessingException {
       // return this.raspberryService.getLockios();
       return this.blockService.getBlock(id).get().getLockio();
    }

    @GetMapping("/blocks/{block-id}/lockios/{local-lockio-id}")
    public Lockio getLockios(@PathVariable("block-id") long id_block,@PathVariable("local-lockio-id") int idLocal ) {

        return this.blockService.getBlock(id_block).get().getLockio().get(idLocal-1);
    }

    @PatchMapping("/blocks/{block-id}/lockios/{local-id}")
    public Lockio patchLockio(@PathVariable("block-id") long id_block,@PathVariable ("local-id") int id_local, @RequestBody String action ){

        Lockio lockio= this.blockService.getBlock(id_block).get().getLockio().get(id_local-1);
        Long global_id= lockio.getId();
        LockioStatus lockioStatus = this.raspberryService.updateStatus(global_id,action);
        this.lockioService.updateStatusLockio(global_id,lockioStatus);
        return lockio;
    }


}
