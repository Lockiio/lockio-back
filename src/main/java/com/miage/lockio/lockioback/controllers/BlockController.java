package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.services.BlockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lockio/1/block/")
public class BlockController {

    private final BlockService blockService;

    public BlockController(BlockService blockService) {
        this.blockService = blockService;
    }

    @GetMapping()
    public List<Block> getBlocks() {
        return this.blockService.getAllBlocks();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Block> addBlock(@RequestBody Block block) {
        this.blockService.addBlock(block);
        if (block.getId() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(block);
    }
}
