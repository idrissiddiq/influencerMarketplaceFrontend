/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Silabus;
import com.example.FinalProject.models.User;
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
public class UserService {
    private RestTemplate restTemplate;
    
    
    @Value("${server.baseUrl}/user")
    private String url;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<User> getAll(){
        ResponseEntity<ResponseListData<User>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<User>>(){} );

        return response.getBody();
    }
    
    public ResponseData<User> getById(Long id){
        ResponseEntity<ResponseData<User>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<User>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<User> create(Silabus data){
        HttpEntity<User> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<User>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<User>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<User> update(Long id, Silabus data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<User>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<User>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<User> delete(Long id){
         
        ResponseEntity<ResponseMessage<User>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<User>>(){} );

        return response.getBody();
    }
    
}
