package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.dao.repositories.BlockRepository;
import com.miage.lockio.lockioback.dao.repositories.LockioRepository;
import com.miage.lockio.lockioback.entities.Block;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RaspberryService {
    private final RestTemplate restTemplate;
    private final LockioRepository lockioRepository;
    private final BlockRepository blockRepository;


    /**
     * Update the status of a lockio. Returns the status of the lockio updated.
     * @param id
     * @param action
     * @return LockioStatus
     */
    public LockioStatus updateStatus(Long id , String action) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(action, headers);
        Lockio lockio = lockioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String block_url = lockio.getBlock().getUrl();
        Lockio lockioToUpdate = this.restTemplate.patchForObject(block_url + "lockios/" + id, request, Lockio.class);
        return lockioToUpdate.getStatus();
    }

    /*
     * TODO : getLockio should be handling the exception if the lockio is not found ?
     *  We also need to handle the routing part on the raspberry, by getting the block_id and local_id
     *  So we can ask the right instance of a block to update the status of the lockio in the database.
     */
    /**
     * Get a unique lockio by its id
     * @param id
     * @return Lockio
     */
    public Lockio getLockio(Long id) {
        Lockio lockioBack = lockioRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        String block_url = lockioBack.getBlock().getUrl();
        Lockio lockio = restTemplate.getForObject(block_url + "lockios/" + id, Lockio.class);
        return lockio;
    }

    /**
     * Get all lockios
     * @return List<Lockio>
     */
    public List<Lockio> getLockios(Long block_id) {
        Block block = blockRepository.findById(block_id).orElseThrow(EntityNotFoundException::new);
        String block_url = block.getUrl();
        ResponseEntity<List<Lockio>> response = restTemplate.exchange(block_url + "lockios/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Lockio>>() {
                });
        List<Lockio> lockios = response.getBody();
        return lockios;
    }


}
