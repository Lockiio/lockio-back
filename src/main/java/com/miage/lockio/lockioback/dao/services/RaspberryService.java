package com.miage.lockio.lockioback.dao.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.lockio.lockioback.entities.Lockio;
import com.miage.lockio.lockioback.enums.LockioStatus;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Service

public class RaspberryService {
    private static final String RASP_PATH = "http://localhost:5000/api/rasp/1/lockios/";
    private final RestTemplate restTemplate ;

    public RaspberryService(RestTemplate restTemplate) {
        this.restTemplate=restTemplate;
    }

    public String updateStatusLockio(long id ) {
        ResponseEntity<Map> response= restTemplate.getForEntity(RASP_PATH + id+"/test", Map.class);
        Map<String, Object> responseBody = response.getBody();
        return responseBody.get("status").toString();

    }

    public LockioStatus updateStatus(long id , String action) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(action, headers);

        Lockio lockio= this.restTemplate.patchForObject(RASP_PATH  +id, request, Lockio.class);
       // Lockio Lockio = response.getBody();

        return lockio.getStatus();
    }


    public Lockio getLockio(long id) {

        Lockio lockio= restTemplate.getForObject(RASP_PATH +id, Lockio.class);
        return lockio;
    }

    public List<Lockio> getLockios() {
        ResponseEntity<Lockio[]> response= restTemplate.exchange(RASP_PATH, HttpMethod.GET, null,Lockio[].class);
        return Arrays.asList(response.getBody());

    }


}
