/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Grade;
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

/**
 *
 * @author Idris Siddiq
 */
@Service
public class GradeService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/grade")
    private String url;

    @Autowired
    public GradeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Grade> getAll(){
        ResponseEntity<ResponseListData<Grade>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Grade>>(){} );

        return response.getBody();
    }
    
    public ResponseData<Grade> getById(String id){
        ResponseEntity<ResponseData<Grade>> response = restTemplate.exchange(url + "/" + id
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Grade>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Grade> create(Grade data){
        HttpEntity<Grade> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<Grade>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<Grade>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<Grade> update(String id, Grade data) {

        data.setName(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<Grade>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<Grade>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<Grade> delete(String id){
         
        ResponseEntity<ResponseMessage<Grade>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Grade>>(){} );

        return response.getBody();
    }
}
