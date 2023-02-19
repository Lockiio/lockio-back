package com.miage.lockio.lockioback.dao.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service

public class RaspberryService {
    private static final String RASP_PATH = "http://localhost:5000/";
    private final RestTemplate restTemplate ;

    public RaspberryService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    public String updateStatusLockio(long id ) {
        ResponseEntity<Map> response= restTemplate.getForEntity(RASP_PATH +"api/rasp/v1/lockio/" +id, Map.class);
        Map<String, Object> responseBody = response.getBody();
        return responseBody.get("status").toString();

    }


}
