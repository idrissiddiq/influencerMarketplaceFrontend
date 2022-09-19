package com.example.FinalProject.services;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.request.CreateCampaignRequest;
import com.example.FinalProject.models.request.UpdateCampaignRequest;
import com.example.FinalProject.models.response.ResponseData;
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

    public ResponseData<Campaign> findById(Long id){
        ResponseEntity<ResponseData<Campaign>> response = restTemplate.exchange(url + "/search/" + id.toString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Campaign>>(){} );
        return response.getBody();
    }

    public ResponseMessage<CreateCampaignRequest> create(CreateCampaignRequest data){
        HttpEntity<CreateCampaignRequest> entity = new HttpEntity(data);
        ResponseEntity<ResponseMessage<CreateCampaignRequest>> response = restTemplate
                .exchange(url + "/create", HttpMethod.POST,
                        entity, new ParameterizedTypeReference<ResponseMessage<CreateCampaignRequest>>(){} );

        return response.getBody();
    }

    public ResponseMessage<UpdateCampaignRequest> update(Long id, UpdateCampaignRequest request) {

        //request.setId(id);
        Campaign campaign = new Campaign();
        campaign.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<UpdateCampaignRequest>> response = restTemplate
                .exchange(url + "/me/" + id, HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<UpdateCampaignRequest>>() {});

        return response.getBody();
    }
}
