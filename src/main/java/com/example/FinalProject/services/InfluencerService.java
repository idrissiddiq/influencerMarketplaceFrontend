package com.example.FinalProject.services;

import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfluencerService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/influencer")
    private String url;

    @Autowired
    public InfluencerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Influencer> findAll(){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url + "/findAllSortByRate", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );
        return response.getBody();
    }
}
