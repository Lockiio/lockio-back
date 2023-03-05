package com.miage.lockio.lockioback;

import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.dao.services.BlockService;
import com.miage.lockio.lockioback.dao.services.LockioCodeService;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.entities.LockioCode;
import com.miage.lockio.lockioback.enums.BlockStatus;
import com.miage.lockio.lockioback.enums.LockioSize;
import com.miage.lockio.lockioback.enums.LockioStatus;
import com.miage.lockio.lockioback.enums.Privacy;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.geo.Point;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class LockioBackApplication implements CommandLineRunner {

    private final BlockService blockService;
    private final LockioService lockioService;
    private final LockioRepository lockioRepository;
    private final LockioCodeService lockioCodeService;

    public static void main(String[] args) {
        SpringApplication.run(LockioBackApplication.class, args);
    }

    public static Block addBlock(BlockService blockService, String name, String url) {
        Block block = new Block();
        block.setName(name);
        block.setCoordinate(new Point(00.00000, 0.0000));
        block.setStatus(BlockStatus.AVAILABLE);
        block.setPrivacy(Privacy.PUBLIC);
        block.setUrl(url);
        blockService.addBlock(block);
        return block;
    }

    public static void addLockios1(LockioService lockioService, Block block) {
        List<Lockio> lockios1 = new ArrayList<>();
        lockios1.add(new Lockio((long) 1, block, (long) 1, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 2, block, (long) 2, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 3, block, (long) 3, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 4, block, (long) 4, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 5, block, (long) 5, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 6, block, (long) 6, LockioSize.MEDIUM, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 7, block, (long) 7, LockioSize.LARGE, LockioStatus.AVAILABLE));
        lockios1.add(new Lockio((long) 8, block, (long) 8, LockioSize.LARGE, LockioStatus.AVAILABLE));
        lockioService.addLockios(lockios1);
    }

    public static void addLockios2(LockioService lockioService, Block block) {
        List<Lockio> lockios2 = new ArrayList<>();
        lockios2.add(new Lockio((long) 9, block, (long) 1, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockios2.add(new Lockio((long) 10, block, (long) 2, LockioSize.SMALL, LockioStatus.AVAILABLE));
        lockioService.addLockios(lockios2);
    }
    @Override
    public void run(String... args) {
        // Add blocks
        Block block1 = LockioBackApplication.addBlock(blockService, "Lockio Paul Sabatier", "http://localhost:8001/api/rasp/1/");
        Block block2 = LockioBackApplication.addBlock(blockService, "Lockio Ramonville", "http://localhost:8002/api/rasp/1/");

        // Add Lockios for each block
        LockioBackApplication.addLockios1(lockioService, block1);
        LockioBackApplication.addLockios2(lockioService, block2);

        // Add Lockio_code for each lockio
        for (Lockio lockio : lockioRepository.findAllByBlockId(block1.getId())) {
            lockioCodeService.addLockioCode(new LockioCode(lockio, block1));
        }

        for (Lockio lockio : lockioRepository.findAllByBlockId(block2.getId())) {
            lockioCodeService.addLockioCode(new LockioCode(lockio, block2));
        }
    }
}
