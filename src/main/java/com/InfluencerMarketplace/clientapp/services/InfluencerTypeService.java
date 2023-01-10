package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.Influencer;
import com.InfluencerMarketplace.clientapp.models.InfluencerType;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfluencerTypeService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/influencerType")
    private String url;

    @Autowired
    public InfluencerTypeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<InfluencerType> findAll(){
        ResponseEntity<ResponseListData<InfluencerType>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<InfluencerType>>(){} );
        return response.getBody();
    }
}
