package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.models.Brand;
import com.InfluencerMarketplace.clientapp.models.request.ChangeProfilePhotoRequest;
import com.InfluencerMarketplace.clientapp.models.request.EditBrandProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BrandService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/brand")
    private String url;

    @Autowired
    public BrandService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseData<Brand> getMyProfileData(){
        ResponseEntity<ResponseData<Brand>> response = restTemplate.exchange(url + "/profile", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<Brand>>(){} );
        return response.getBody();
    }

    public ResponseData<ChangeProfilePhotoRequest> getMyProfilePhotoPath(){
        ResponseEntity<ResponseData<ChangeProfilePhotoRequest>> response = restTemplate.exchange(url + "/profile/photo", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseData<ChangeProfilePhotoRequest>>(){} );
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

    public ResponseMessage<EditBrandProfileRequest> editMyProfile(EditBrandProfileRequest request) {

        //request.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<EditBrandProfileRequest>> response = restTemplate
                .exchange(url + "/profile", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<EditBrandProfileRequest>>() {});
        return response.getBody();
    }
}
