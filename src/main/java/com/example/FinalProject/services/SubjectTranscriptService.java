/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.SubjectTranscript;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.STSRapot;
import com.example.FinalProject.models.response.SubjectTranscriptTrainer;
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
public class SubjectTranscriptService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/subjectTranscript")
    private String url;

    @Autowired
    public SubjectTranscriptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<SubjectTranscript> getAll(){
        ResponseEntity<ResponseListData<SubjectTranscript>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<SubjectTranscript>>(){} );

        return response.getBody();
    }
    
    public ResponseData<SubjectTranscript> getById(Long id){
        ResponseEntity<ResponseData<SubjectTranscript>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<SubjectTranscript>>(){} );

        return response.getBody();
    }
    
    
    //nilai persubject -peserta 
    public ResponseListData<SubjectTranscript> getByTranscriptId(){
        ResponseEntity<ResponseListData<SubjectTranscript>> response = restTemplate.exchange(url + "/transcript" 
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubjectTranscript>>(){} );

        return response.getBody();
    }
    
    //nilai subject transcript per kelas - trainer
    public ResponseListData<SubjectTranscriptTrainer> findSTSByClass(String id){
        ResponseEntity<ResponseListData<SubjectTranscriptTrainer>> response = restTemplate.exchange(url + "/class/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubjectTranscriptTrainer>>(){} );

        return response.getBody();
    }
    
    //Rapot STS
    public ResponseListData<STSRapot> RapotSTS(){
        ResponseEntity<ResponseListData<STSRapot>> response = restTemplate.exchange(url + "/rapot"
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<STSRapot>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<SubjectTranscript> create(SubjectTranscript data){
        HttpEntity<SubjectTranscript> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<SubjectTranscript>> response = restTemplate
                .exchange(url, HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<SubjectTranscript>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<SubjectTranscript> update(Long id, SubjectTranscript data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<SubjectTranscript>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<SubjectTranscript>>() {});

        return response.getBody();
    }
    
    public ResponseMessage<SubjectTranscript> delete(Long id){
         
        ResponseEntity<ResponseMessage<SubjectTranscript>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<SubjectTranscript>>(){} );

        return response.getBody();
    }
}
