/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Job;
import com.example.FinalProject.models.Kelas;
import com.example.FinalProject.models.response.KelasRequest;
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
 * @author ASUS
 */
@Service
public class KelasService {
    private RestTemplate restTemplate;
    
    @Value("${server.baseUrl}/kelas")
    private String url;

    @Autowired
    public KelasService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public ResponseListData<Kelas> getAll(){
        ResponseEntity<ResponseListData<Kelas>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<Kelas>>(){} );

        return response.getBody();
    }
    
    public ResponseData<Kelas> getById(String id){
        ResponseEntity<ResponseData<Kelas>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Kelas>>(){} );

        return response.getBody();
    }
    
    //kelas trainer
    public ResponseListData<Kelas> findAllByTrainer(){
        ResponseEntity<ResponseListData<Kelas>> response = restTemplate.exchange(url + "/trainer" 
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<Kelas>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<KelasRequest> create(KelasRequest data){
        HttpEntity<KelasRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<KelasRequest>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<KelasRequest>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<KelasRequest> update(String id, KelasRequest data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<KelasRequest>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<KelasRequest>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<Kelas> delete(String id){
         
        ResponseEntity<ResponseMessage<Kelas>> response = restTemplate.exchange(url + "/" + id
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Kelas>>(){} );

        return response.getBody();
    }
    
}
