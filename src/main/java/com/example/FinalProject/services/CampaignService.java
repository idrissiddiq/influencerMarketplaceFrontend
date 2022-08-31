package com.example.FinalProject.services;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.Grade;
import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.request.createCampaignRequest;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CampaignService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/campaign")
    private String url;

    @Autowired
    public CampaignService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Campaign> findAllOnlyOpen(){
        ResponseEntity<ResponseListData<Campaign>> response = restTemplate.exchange(url + "/1", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Campaign>>(){} );
        return response.getBody();
    }

    public ResponseListData<Campaign> findMyCampaign(){
        ResponseEntity<ResponseListData<Campaign>> response = restTemplate.exchange(url + "/me", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Campaign>>(){} );
        return response.getBody();
    }

    public String create(createCampaignRequest data){
        HttpEntity<createCampaignRequest> entity = new HttpEntity(data);
        ResponseEntity<ResponseMessage<createCampaignRequest>> response = restTemplate
                .exchange(url + "/create", HttpMethod.POST,
                        entity, new ParameterizedTypeReference<ResponseMessage<createCampaignRequest>>(){} );

        return "Create Success";
    }
}
