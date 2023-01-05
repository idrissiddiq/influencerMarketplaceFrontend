package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.response.*;
import com.InfluencerMarketplace.clientapp.models.Influencer;
import com.InfluencerMarketplace.clientapp.models.InfluencerFilePath;
import com.InfluencerMarketplace.clientapp.models.InfluencerType;
import com.InfluencerMarketplace.clientapp.models.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketplace.clientapp.models.request.EditInfluencerProfileRequest;
import com.InfluencerMarketplace.clientapp.models.response.*;
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

    public ResponseListData<CalculateAgeResponse> findAge(){
        ResponseEntity<ResponseListData<CalculateAgeResponse>> response = restTemplate.exchange(url + "/findAgeSortByRate", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<CalculateAgeResponse>>(){} );
        return response.getBody();
    }

    public ResponseListData<FindAllInfluencerResponse> findAllInfluencer(){
        ResponseEntity<ResponseListData<FindAllInfluencerResponse>> response = restTemplate.exchange(url + "/findAllInfluencer", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<FindAllInfluencerResponse>>(){} );
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

    public InfluencerFilePath getProfilePhotoById (Long id){
        ResponseEntity<InfluencerFilePath> response = restTemplate.exchange(url + "/profile/photo/" + id.toString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<InfluencerFilePath>(){} );
        return response.getBody();
    }

    public ResponseData<Influencer> getMyProfileData(){
        ResponseEntity<ResponseData<Influencer>> response = restTemplate.exchange(url + "/profile", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Influencer>>(){} );
        return response.getBody();
    }

    public ResponseListData<InfluencerType> getMyType(){
        ResponseEntity<ResponseListData<InfluencerType>> response = restTemplate.exchange(url + "/mytype", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<InfluencerType>>(){} );
        return response.getBody();
    }

    public ResponseMessage<EditInfluencerProfileRequest> editMyProfile(EditInfluencerProfileRequest request) {

        //request.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<EditInfluencerProfileRequest>> response = restTemplate
                .exchange(url + "/profile", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<EditInfluencerProfileRequest>>() {});
        return response.getBody();
    }
}
