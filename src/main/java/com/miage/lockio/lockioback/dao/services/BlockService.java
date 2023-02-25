package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;
    public void addBlock(Block block) {
        this.blockRepository.save(block);
    }

}
