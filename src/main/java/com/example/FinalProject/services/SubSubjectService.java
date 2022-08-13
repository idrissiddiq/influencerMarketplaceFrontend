/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.SubSubject;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SubSubjectDTO;
import com.example.FinalProject.models.response.SubSubjectRequest;

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
public class SubSubjectService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/subsubject")
    private String url;

    @Autowired
    public SubSubjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<SubSubject> getAll(){
        ResponseEntity<ResponseListData<SubSubject>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<SubSubject>>(){} );

        return response.getBody();
    }
    
    public ResponseListData<SubSubject> findBySubjectId(Long id){
        ResponseEntity<ResponseListData<SubSubject>> response = restTemplate.exchange(url + "/subject/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubSubject>>(){} );

        return response.getBody();
    }
    
    //find by subject - trainer
     public ResponseListData<SubSubjectDTO> findsSubSubjectBySubjectId(Long classId){
        ResponseEntity<ResponseListData<SubSubjectDTO>> response = restTemplate.exchange(url + "/subjectTrainer/" + classId.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubSubjectDTO>>(){} );

        return response.getBody();
    }
    
    public ResponseData<SubSubject> getById(Long id){
        ResponseEntity<ResponseData<SubSubject>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<SubSubject>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<SubSubjectRequest> create(SubSubjectRequest data){
        HttpEntity<SubSubjectRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<SubSubjectRequest>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<SubSubjectRequest>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<SubSubjectRequest> update(Long id, SubSubjectRequest data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<SubSubjectRequest>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<SubSubjectRequest>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<SubSubject> delete(Long id){
         
        ResponseEntity<ResponseMessage<SubSubject>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<SubSubject>>(){} );

        return response.getBody();
    }
}
