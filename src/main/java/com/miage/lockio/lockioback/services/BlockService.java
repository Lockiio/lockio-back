package com.miage.lockio.lockioback.services;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.repositories.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public List<Block> getAllBlocks() {
        return this.blockRepository.findAll();
    }

    public Optional<Block> getBlock(long id) {
        return this.blockRepository.findById(id);
    }

    public void addBlock(Block Block) {
        this.blockRepository.save(Block);
    }
}
