package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.request.ChangeRegisterRequest;
import com.InfluencerMarketplace.clientapp.models.request.ForgotPasswordRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.models.request.RegisterBrandRequest;
import com.InfluencerMarketplace.clientapp.models.request.RegisterInfluencerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegisterService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/register/influencer")
    private String url;

    @Value("${server.baseUrl}/register/brand")
    private String urlBrand;

    @Autowired
    public RegisterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseMessage<ForgotPasswordRequest> forgotPassword(ForgotPasswordRequest request) {

        //request.setId(id);
//        Campaign campaign = new Campaign();
//        campaign.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<ForgotPasswordRequest>> response = restTemplate
                .exchange(url + "/forgot", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<ForgotPasswordRequest>>() {});

        return response.getBody();
    }

    public ResponseMessage<ForgotPasswordRequest> forgotPasswordBrand(ForgotPasswordRequest request) {

        //request.setId(id);
//        Campaign campaign = new Campaign();
//        campaign.setId(id);
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<ForgotPasswordRequest>> response = restTemplate
                .exchange(urlBrand + "/forgot", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<ResponseMessage<ForgotPasswordRequest>>() {});

        return response.getBody();
    }

    public ResponseMessage<RegisterInfluencerRequest> registerInflunecer(RegisterInfluencerRequest request) {
        HttpEntity<RegisterInfluencerRequest> entity = new HttpEntity<>(request);
        return restTemplate
                .exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseMessage<RegisterInfluencerRequest>>() {
                }).getBody();
    }

    public ResponseMessage<RegisterBrandRequest> registerBrand(RegisterBrandRequest request) {
        HttpEntity<RegisterBrandRequest> entity = new HttpEntity<>(request);
        return restTemplate
                .exchange(urlBrand, HttpMethod.POST, entity, new ParameterizedTypeReference<ResponseMessage<RegisterBrandRequest>>() {
                }).getBody();
    }
    public String changeRegister(ChangeRegisterRequest request) {

        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<String> response = restTemplate
                .exchange(url + "/change", HttpMethod.PUT, new HttpEntity(request), new
                        ParameterizedTypeReference<String>() {});

        return response.getBody();
    }

}
