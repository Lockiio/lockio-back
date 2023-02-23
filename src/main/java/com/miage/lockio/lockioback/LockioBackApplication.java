package com.miage.lockio.lockioback;

import com.miage.lockio.lockioback.dao.services.BlockService;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.LockioSize;
import com.miage.lockio.lockioback.enums.LockioStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class LockioBackApplication implements CommandLineRunner {

    private BlockService blockService;
    private LockioService lockioService;

    public LockioBackApplication(BlockService blockService, LockioService lockioService) {
        this.blockService = blockService;
        this.lockioService = lockioService;
    }


    public static void main(String[] args) {
        SpringApplication.run(LockioBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Block block = new Block();
        block.setName("LockioPaulSab");
        block.setCoordinate(new Point(00.00000, 0.0000));
        block.setStatus(BlockStatus.AVAILABLE);
        block.setPrivacy(Privacy.PUBLIC);
        blockService.addBlock(block);
        List<Lockio> lockios = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            Lockio lockio = new Lockio();
            lockio.setLocalId(i);
            lockio.setSize(LockioSize.SMALL);
            lockio.setStatus(LockioStatus.AVAILABLE);
            lockio.setBlock(block);
            lockios.add(lockio);
            lockioService.addLockio(lockio);
        }
        block.setLockio(lockios);
    }
}
