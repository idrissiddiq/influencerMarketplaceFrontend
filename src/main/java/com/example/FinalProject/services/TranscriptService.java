/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Grade;
import com.example.FinalProject.models.Transcript;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.TranscriptData;
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
 * @author Idris Siddiq
 */
@Service
public class TranscriptService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/transcript")
    private String url;

    @Autowired
    public TranscriptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Transcript> getAll(){
        ResponseEntity<ResponseListData<Transcript>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Transcript>>(){} );

        return response.getBody();
    }
    
    public ResponseData<TranscriptData> getById(){
        ResponseEntity<ResponseData<TranscriptData>> response = restTemplate.exchange(url + "/gradeAll"
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<TranscriptData>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Transcript> create(Transcript data){
        HttpEntity<Grade> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<Transcript>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<Transcript>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<Grade> update(Long id, Transcript data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<Grade>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<Grade>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<Transcript> delete(Long id){
         
        ResponseEntity<ResponseMessage<Transcript>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Transcript>>(){} );

        return response.getBody();
    }
}
