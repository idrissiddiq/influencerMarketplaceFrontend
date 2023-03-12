package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.request.ApproveContractRequest;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.Contract;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

    public ResponseMessage<String> createContract(Long id){
        ResponseEntity<ResponseMessage<String>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.POST,
                        null, new ParameterizedTypeReference<ResponseMessage<String>>(){} );
        return response.getBody();
    }

    public ResponseMessage<String> approveContract(ApproveContractRequest request, Long id){
        HttpEntity entity = new HttpEntity(request);
        ResponseEntity<ResponseMessage<String>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT,
                        entity, new ParameterizedTypeReference<ResponseMessage<String>>(){} );
        return response.getBody();
    }
}
