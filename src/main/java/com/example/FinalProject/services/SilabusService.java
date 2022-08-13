/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Silabus;
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
 * @author Sendy
 */
@Service
public class SilabusService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/silabus")
    private String url;

    @Autowired
    public SilabusService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Silabus> getAll(){
        ResponseEntity<ResponseListData<Silabus>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Silabus>>(){} );

        return response.getBody();
    }
    
    public ResponseData<Silabus> getById(Long id){
        ResponseEntity<ResponseData<Silabus>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Silabus>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Silabus> create(Silabus data){
        HttpEntity<Silabus> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<Silabus>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<Silabus>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<Silabus> update(Long id, Silabus data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<Silabus>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<Silabus>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<Silabus> delete(Long id){
         
        ResponseEntity<ResponseMessage<Silabus>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Silabus>>(){} );

        return response.getBody();
    }
    
}
