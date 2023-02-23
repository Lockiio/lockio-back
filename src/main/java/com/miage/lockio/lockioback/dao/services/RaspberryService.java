package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class RaspberryService {
    private static final String RASP_PATH = "http://localhost:5000/api/rasp/1/lockios/";
    private final RestTemplate restTemplate ;

    public RaspberryService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

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
        Lockio lockio = this.restTemplate.patchForObject(RASP_PATH + id, request, Lockio.class);
        return lockio.getStatus();
    }

    /**
     * Get a unique lockio by id
     * @param id
     * @return Lockio
     */
    public Lockio getLockio(Long id) {
        Lockio lockio = restTemplate.getForObject(RASP_PATH + id, Lockio.class);
        return lockio;
    }

    /**
     * Get all lockios
     * @return List<Lockio>
     */
    public List<Lockio> getLockios() {
        ResponseEntity<List<Lockio>> response = restTemplate.exchange(RASP_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Lockio>>() {
                });
        List<Lockio> lockios = response.getBody();
        System.out.println(lockios);
        return lockios;
    }


}
