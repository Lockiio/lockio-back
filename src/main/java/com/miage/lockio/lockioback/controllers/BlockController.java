package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.dao.services.BlockService;
import com.miage.lockio.lockioback.entities.Lockio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lockio/v1/block")
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping()
    public List<Block> getBlocks() {
        return this.blockService.getAllBlocks();
    }

    @GetMapping("/{block-id}")
    public Optional<Block> getBlocks(@PathVariable ("block-id") long id) {
        return this.blockService.getBlock(id);
    }

    @GetMapping("/blocks/{block-id}/lockios")
    public Collection<Lockio> getLockios(@PathVariable("block-id") long id) {
        return this.blockService.getBlock(id).get().getLockio();
    }


}
