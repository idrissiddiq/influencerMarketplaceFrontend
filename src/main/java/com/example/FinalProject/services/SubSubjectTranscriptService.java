/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.SubSubjectTranscript;
import com.example.FinalProject.models.SubjectTranscript;
import com.example.FinalProject.models.response.NilaiSSTS;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SSTSRapot;
import com.example.FinalProject.models.response.STSRequest;
import com.example.FinalProject.models.response.SubSTSTrainer;
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
public class SubSubjectTranscriptService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/subsubject_transcript")
    private String url;

    @Autowired
    public SubSubjectTranscriptService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<SubSubjectTranscript> getAll(){
        ResponseEntity<ResponseListData<SubSubjectTranscript>> response = restTemplate.exchange(url, HttpMethod.GET, 
                null, new ParameterizedTypeReference<ResponseListData<SubSubjectTranscript>>(){} );

        return response.getBody();
    }
    
    //nilai persubject -peserta 
    public ResponseListData<SubSubjectTranscript> getBySubjectTranscriptId(Long id){
        ResponseEntity<ResponseListData<SubSubjectTranscript>> response = restTemplate.exchange(url + "/subject/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubSubjectTranscript>>(){} );

        return response.getBody();
    }
    
    //nilai SUBsubject transcript per SUBJECT - trainer
    public ResponseListData<SubSTSTrainer> findSSTSBySubject(String classid, Long id){
        ResponseEntity<ResponseListData<SubSTSTrainer>> response = restTemplate.exchange(url + "/" + classid + "/"+ id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubSTSTrainer>>(){} );

        return response.getBody();
    }
    
    //nilai per SUB subject -trainer 
    public ResponseListData<NilaiSSTS> findAllBySubSubject(String classid, Long id){
        ResponseEntity<ResponseListData<NilaiSSTS>> response = restTemplate.exchange(url + "/SubSubject/" + classid + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<NilaiSSTS>>(){} );

        return response.getBody();
    }
    
    //nilai per peserta - trainer
    public ResponseData<SubSubjectTranscript> getById(Long id){
        ResponseEntity<ResponseData<SubSubjectTranscript>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<SubSubjectTranscript>>(){} );

        return response.getBody();
    }
    
    //Rapot STS
    public ResponseListData<SSTSRapot> RapotSSTS(){
        ResponseEntity<ResponseListData<SSTSRapot>> response = restTemplate.exchange(url + "/rapot" 
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SSTSRapot>>(){} );

        return response.getBody();
    }
    
//    public ResponseMessage<NilaiSSTS> update(Long id, NilaiSSTS data) {
//
//        data.setSubSubjectId(id);
//        HttpEntity entity = new HttpEntity(data);
//
//         ResponseEntity<ResponseMessage<NilaiSSTS>> response = restTemplate
//                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
//        ParameterizedTypeReference<ResponseMessage<NilaiSSTS>>() {});
//
//        return response.getBody();
//    }
    
    //
    
    //input nilai
    public ResponseMessage<STSRequest> updateNilai(STSRequest data){
        HttpEntity<STSRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<STSRequest>> response = restTemplate
                .exchange(url + "/inputnilai", HttpMethod.PUT, 
                entity, new ParameterizedTypeReference<ResponseMessage<STSRequest>>(){} );

        return response.getBody();
    }
    
    
    
//    
//    public ResponseMessage<SubSubjectTranscript> create(SubSubjectTranscript data){
//        HttpEntity<SubSubjectTranscript> entity = new HttpEntity(data);
//        
//        ResponseEntity<ResponseMessage<SubSubjectTranscript>> response = restTemplate
//                .exchange(url, HttpMethod.POST, 
//                entity, new ParameterizedTypeReference<ResponseMessage<SubSubjectTranscript>>(){} );
//
//        return response.getBody();
//    }
//    
//
//    
//    public ResponseMessage<SubSubjectTranscript> delete(Long id){
//         
//        ResponseEntity<ResponseMessage<SubSubjectTranscript>> response = restTemplate.exchange(url + "/" + id.toString()
//            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<SubSubjectTranscript>>(){} );
//
//        return response.getBody();
//    }
}
