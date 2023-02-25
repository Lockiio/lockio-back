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
        //TODO : remove this code and create an SQL script to create the blocks and the lockios (at least the prototype one)
        Block block1 = new Block();
        block1.setName("Lockio Paul Sabatier");
        block1.setCoordinate(new Point(00.00000, 0.0000));
        block1.setStatus(BlockStatus.AVAILABLE);
        block1.setPrivacy(Privacy.PUBLIC);
        block1.setUrl("http://localhost:8001/api/rasp/1/");
        blockService.addBlock(block1);
        List<Lockio> lockios1 = new ArrayList<>();
        lockios1.add(new Lockio((long) 1, block1, (long) 1, LockioSize.SMALL, LockioStatus.OCCUPIED));
        lockios1.add(new Lockio((long) 2, block1, (long) 2, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 3, block1, (long) 3, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 4, block1, (long) 4, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 5, block1, (long) 5, LockioSize.MEDIUM, LockioStatus.OCCUPIED));
        lockios1.add(new Lockio((long) 6, block1, (long) 6, LockioSize.MEDIUM, LockioStatus.OCCUPIED));
        lockios1.add(new Lockio((long) 7, block1, (long) 7, LockioSize.LARGE, LockioStatus.DISABLED));
        lockios1.add(new Lockio((long) 8, block1, (long) 8, LockioSize.LARGE, LockioStatus.DISABLED));
        lockioService.addLockios(lockios1);
        block1.setLockio(lockios1);

        Block block2 = new Block();
        block2.setName("Lockio Ramonville");
        block2.setCoordinate(new Point(00.00000, 0.0000));
        block2.setStatus(BlockStatus.AVAILABLE);
        block2.setPrivacy(Privacy.PUBLIC);
        block2.setUrl("http://localhost:8002/api/rasp/1/");
        blockService.addBlock(block2);
        List<Lockio> lockios2 = new ArrayList<>();
        lockios2.add(new Lockio((long) 1, block2, (long) 1, LockioSize.SMALL, LockioStatus.OCCUPIED));
        lockios2.add(new Lockio((long) 2, block2, (long) 2, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockioService.addLockios(lockios2);
        block1.setLockio(lockios2);
    }
}
