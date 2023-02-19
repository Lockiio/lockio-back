package com.miage.lockio.lockioback.dao.services;

import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service

public class RaspberryService {
    private static final String RASP_PATH = "http://localhost:5000/api/rasp/v1/lockio/";
    private final RestTemplate restTemplate ;

    public RaspberryService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    public String updateStatusLockio(long id ) {
        ResponseEntity<Map> response= restTemplate.getForEntity(RASP_PATH + +id+"/test", Map.class);
        Map<String, Object> responseBody = response.getBody();
        return responseBody.get("status").toString();

    }

    public ResponseEntity<Lockio> updateStatus(long id , String lockioStatus) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requête = new HttpEntity<>(lockioStatus, headers);
        return restTemplate.exchange( RASP_PATH  +id, HttpMethod.PATCH,requête, Lockio.class);


    }


}
