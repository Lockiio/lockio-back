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
    public void run(String... args) {
        Block block = new Block();
        block.setName("LockioPaulSab");
        block.setCoordinate(new Point(00.00000, 0.0000));
        block.setStatus(BlockStatus.AVAILABLE);
        block.setPrivacy(Privacy.PUBLIC);
        blockService.addBlock(block);
        List<Lockio> lockios = new ArrayList<>();
        lockios.add(new Lockio((long) 1, block, (long) 1, LockioSize.SMALL, LockioStatus.OCCUPIED));
        lockios.add(new Lockio((long) 2, block, (long) 2, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockios.add(new Lockio((long) 3, block, (long) 3, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios.add(new Lockio((long) 4, block, (long) 4, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios.add(new Lockio((long) 5, block, (long) 5, LockioSize.MEDIUM, LockioStatus.OCCUPIED));
        lockios.add(new Lockio((long) 6, block, (long) 6, LockioSize.MEDIUM, LockioStatus.OCCUPIED));
        lockios.add(new Lockio((long) 7, block, (long) 7, LockioSize.LARGE, LockioStatus.DISABLED));
        lockios.add(new Lockio((long) 8, block, (long) 8, LockioSize.LARGE, LockioStatus.DISABLED));
        lockioService.addLockios(lockios);
        block.setLockio(lockios);
    }
}
