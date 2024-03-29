package com.miage.lockio.lockioback.controllers;

import com.miage.lockio.lockioback.api.ApiStatusCodeLockio;
import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import com.miage.lockio.lockioback.dao.repositories.LockioCodeRepository;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.dao.services.LockioCodeService;
import com.miage.lockio.lockioback.dao.services.LockioService;
import com.miage.lockio.lockioback.dao.services.RaspberryService;
import com.miage.lockio.lockioback.api.ApiBlock;
import com.miage.lockio.lockioback.api.ApiLockio;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.entities.LockioCode;
import com.miage.lockio.lockioback.enums.LockioStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lockio/1/blocks")
public class BlockController {

    private final LockioService lockioService;
    private final LockioRepository lockioRepository;
    private final LockioCodeRepository lockioCodeRepository;
    private final BlockRepository blockRepository;
    private final RaspberryService raspberryService;

    private final LockioCodeService lockioCodeService;

    @GetMapping("")
    public List<ApiBlock> getBlocks() {
        return this.blockRepository.findAll().stream().map(ApiBlock::new).toList();
    }

    @GetMapping("/{block-id}")

    public ApiBlock getBlock(@PathVariable("block-id") Long blockId) {
        return new ApiBlock(this.blockRepository.findById(blockId).get());
    }

    @GetMapping("/{block-id}/lockios")
    public List<ApiLockio> getAllLockios(@PathVariable("block-id") Long blockId) {
        // Call to raspberry to update lockios
        List<Lockio> lockios = this.raspberryService.getLockios(blockId);
        for (Lockio lockio : lockios) {
            this.lockioService.updateLockioStatus(lockio.getId(), lockio.getStatus());
        }
        return this.lockioRepository.findAllByBlockId(blockId).stream().map(ApiLockio::new).toList();
    }

    @GetMapping("/{block-id}/lockios/{local-lockio-id}")
    public ApiLockio getLockio(@PathVariable("block-id") Long blockId, @PathVariable("local-lockio-id") Long localId) {
        Lockio lockio = this.raspberryService.getLockio(localId);
        this.lockioService.updateLockioStatus(lockio.getId(), lockio.getStatus());
        return new ApiLockio(this.lockioRepository.findByBlockIdAndLocalId(blockId,localId));

    }

    @PatchMapping("/{block-id}/lockios/{local-lockio-id}")
    public ApiStatusCodeLockio patchLockio(@PathVariable("block-id") Long blockId, @PathVariable("local-lockio-id") Long localId, @RequestBody Map<String, String> body) {
        Lockio lockio = this.lockioRepository.findByBlockIdAndLocalId(blockId, localId);
        LockioCode lockioCode = this.lockioCodeRepository.findByLockio(lockio);
        // If status is available and code is not null, we update the code
        boolean isAvailable = lockio.getStatus().equals(LockioStatus.AVAILABLE) && body.get("status").equals(LockioStatus.AVAILABLE.toString());
        if (isAvailable && body.get("code") == null) {
            String newCode = this.lockioCodeService.updateLockioCode(lockioCode);
            this.raspberryService.updateStatus(lockio.getId(), LockioStatus.OCCUPIED);
            this.lockioService.updateLockioStatus(lockio.getId(), LockioStatus.OCCUPIED);
            return new ApiStatusCodeLockio(lockio.getStatus().toString(), newCode);
        } else if (body.get("code") != null) {
            if (this.lockioCodeService.resetLockioCode(lockioCode, body.get("code"))) {
                this.raspberryService.updateStatus(lockio.getId(), LockioStatus.AVAILABLE);
                this.lockioService.updateLockioStatus(lockio.getId(), LockioStatus.AVAILABLE);
                return new ApiStatusCodeLockio(lockio.getStatus().toString(), null);
            };
        }
        return new ApiStatusCodeLockio(lockio.getStatus().toString(), null);
    }


    @GetMapping("/{block-id}/lockios/local")
    public List<Lockio> getAllLockiosLocal(@PathVariable("block-id") Long id) {
        return this.lockioRepository.findAllByBlockId(id);
    }
}
