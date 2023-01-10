package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.Campaign;
import com.InfluencerMarketplace.clientapp.models.CampaignStatus;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CampaignStatusService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/campaignStatus")
    private String url;

    @Autowired
    public CampaignStatusService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<CampaignStatus> findAll(){
        ResponseEntity<ResponseListData<CampaignStatus>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<CampaignStatus>>(){} );
        return response.getBody();
    }
}
