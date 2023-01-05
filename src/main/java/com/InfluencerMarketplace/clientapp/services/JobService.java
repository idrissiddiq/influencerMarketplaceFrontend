/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.InfluencerMarketplace.clientapp.services;

import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ASUS
 */
@Service
public class JobService {
    private RestTemplate restTemplate;
    
    @Value("${server.baseUrl}/job")
    private String url;

    @Autowired
    public JobService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseListData<Job> getAll(){
        ResponseEntity<ResponseListData<Job>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Job>>(){} );

        return response.getBody();
    }
    
    public ResponseListData<Job> getAllExcept(){
        ResponseEntity<ResponseListData<Job>> response = restTemplate.exchange(url + "/except", HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Job>>(){} );

        return response.getBody();
    }
    
    public ResponseData<Job> getById(String id){
        ResponseEntity<ResponseData<Job>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Job>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Job> create(Job data){
        HttpEntity<Job> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<Job>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<Job>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<Job> update(String id, Job data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<Job>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<Job>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<Job> delete(String id){
         
        ResponseEntity<ResponseMessage<Job>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Job>>(){} );

        return response.getBody();
    }
    
}
