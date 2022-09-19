package com.example.FinalProject.services;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.Contract;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContractService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/contract")
    private String url;

    @Autowired
    public ContractService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Contract> findAllMyContractByCampaign(Long id){
        ResponseEntity<ResponseListData<Contract>> response = restTemplate.exchange(url + "/" + id.toString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Contract>>(){} );
        return response.getBody();
    }

    public ResponseListData<Contract> findMyContractByInfluencer(){
        ResponseEntity<ResponseListData<Contract>> response = restTemplate.exchange(url + "/me", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Contract>>(){} );
        return response.getBody();
    }
}
