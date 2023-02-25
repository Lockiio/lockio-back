package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class BlockService {
    private final BlockRepository blockRepository;
    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }
    public void addBlock(Block block) {
        this.blockRepository.save(block);
    }

}
