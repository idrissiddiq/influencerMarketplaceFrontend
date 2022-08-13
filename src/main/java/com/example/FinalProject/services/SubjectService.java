/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Silabus;
import com.example.FinalProject.models.Subject;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SubjectDTO;
import com.example.FinalProject.models.response.SubjectRequest;
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
public class SubjectService {

    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/subject")
    private String url;

    @Autowired
    public SubjectService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseListData<Subject> getAll() {
        ResponseEntity<ResponseListData<Subject>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Subject>>() {
        });

        return response.getBody();
    }

    public ResponseData<Subject> getById(Long id) {
        ResponseEntity<ResponseData<Subject>> response = restTemplate.exchange(url + "/" + id.toString(),
                 HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Subject>>() {
        });

        return response.getBody();
    }

    //find by silabus - admin
    public ResponseListData<Subject> findBySilabusId(Long id) {
        ResponseEntity<ResponseListData<Subject>> response = restTemplate.exchange(url + "/silabus/" + id.toString(),
                 HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<Subject>>() {
        });

        return response.getBody();
    }

    //find by class - trainer
    public ResponseListData<SubjectDTO> findSubjectByClassId(String id) {
        ResponseEntity<ResponseListData<SubjectDTO>> response = restTemplate.exchange(url + "/class/" + id.toString(),
                 HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubjectDTO>>() {
        });

        return response.getBody();
    }

    public ResponseListData<SubjectDTO> findSilabusByClassId(String classId) {
        ResponseEntity<ResponseListData<SubjectDTO>> response = restTemplate.exchange(url + "/dto/class/" + classId,
                 HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<SubjectDTO>>() {
        });

        return response.getBody();
    }

    public ResponseMessage<SubjectRequest> create(SubjectRequest data) {
        HttpEntity<SubjectRequest> entity = new HttpEntity(data);

        ResponseEntity<ResponseMessage<SubjectRequest>> response = restTemplate
                .exchange(url, HttpMethod.POST,
                        entity, new ParameterizedTypeReference<ResponseMessage<SubjectRequest>>() {
                });

        return response.getBody();
    }

    public ResponseMessage<SubjectRequest> update(Long id, SubjectRequest data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

        ResponseEntity<ResponseMessage<SubjectRequest>> response = restTemplate
                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new ParameterizedTypeReference<ResponseMessage<SubjectRequest>>() {
                });

        return response.getBody();
    }

    public ResponseMessage<Subject> delete(Long id) {

        ResponseEntity<ResponseMessage<Subject>> response = restTemplate.exchange(url + "/" + id.toString(),
                 HttpMethod.DELETE, null, new ParameterizedTypeReference<ResponseMessage<Subject>>() {
        });

        return response.getBody();
    }

}
