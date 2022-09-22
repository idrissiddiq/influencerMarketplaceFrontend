package com.example.FinalProject.services;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.InfluencerFilePath;
import com.example.FinalProject.models.request.ChangeProfilePhotoRequest;
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

    public ResponseMessage<ChangeProfilePhotoRequest> changeProfilePhoto(ChangeProfilePhotoRequest request) {

        //request.setId(id);
//        InfluencerFilePath influencerFilePath = new InfluencerFilePath();
//        campaign.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<ChangeProfilePhotoRequest>> response = restTemplate
                .exchange(url + "/profile/photo", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<ChangeProfilePhotoRequest>>() {});

        return response.getBody();
    }

    public ResponseData<ChangeProfilePhotoRequest> getMyProfilePhotoPath(){
        ResponseEntity<ResponseData<ChangeProfilePhotoRequest>> response = restTemplate.exchange(url + "/profile/photo", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<ChangeProfilePhotoRequest>>(){} );
        return response.getBody();
    }

    public ResponseData<Influencer> getMyProfileData(){
        ResponseEntity<ResponseData<Influencer>> response = restTemplate.exchange(url + "/profile", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Influencer>>(){} );
        return response.getBody();
    }
}
