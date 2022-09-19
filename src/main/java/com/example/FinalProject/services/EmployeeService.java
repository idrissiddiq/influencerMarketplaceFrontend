/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.services;

import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.response.AdminDashboardDTO;
import com.example.FinalProject.models.response.AssignTrainerRequest;
import com.example.FinalProject.models.response.ChangePasswordDTO;
import com.example.FinalProject.models.response.EmployeeRequest;
import com.example.FinalProject.models.response.EmployeeResponse;
import com.example.FinalProject.models.response.PesertaRequest;
import com.example.FinalProject.models.response.ProfileDTO;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.TrainerDashboardDTO;
import com.example.FinalProject.models.response.TrainerResponse;

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
public class EmployeeService {
    private RestTemplate restTemplate;

    @Value("${server.baseUrl}/employee")
    private String url;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
     
    public ResponseListData<Influencer> getAll(){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url, HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );

        return response.getBody();
    }
    
    public ResponseListData<Influencer> getAllExcept(){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url + "/except", HttpMethod.GET,
                null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );

        return response.getBody();
    }
    
     public ResponseListData<Influencer> findEmployeeByClassId(String id){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url + "/class/peserta/" + id
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );

        return response.getBody();
    }

    public ResponseListData<Influencer> findAllAvailablePeserta(){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url + "/available_peserta"
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );

        return response.getBody();
    }
    

    public ResponseListData<Influencer> findAllTrainer(){
        ResponseEntity<ResponseListData<Influencer>> response = restTemplate.exchange(url + "/all_trainer"
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<Influencer>>(){} );

        return response.getBody();
    }

    public ResponseData<AdminDashboardDTO> getDashboardAdminInfo(){
        ResponseEntity<ResponseData<AdminDashboardDTO>> response = restTemplate.exchange(url + "/dashboard_admin"
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<AdminDashboardDTO>>(){} );

        return response.getBody();
    }

    public ResponseData<TrainerDashboardDTO> getDashboardTrainerInfo(String name){
        ResponseEntity<ResponseData<TrainerDashboardDTO>> response = restTemplate.exchange(url + "/dashboard_trainer/" + name 
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<TrainerDashboardDTO>>(){} );

        return response.getBody();
    }

    public ResponseData<ProfileDTO> getUserProfile(String name){
        ResponseEntity<ResponseData<ProfileDTO>> response = restTemplate.exchange(url + "/profile/" + name
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<ProfileDTO>>(){} );

        return response.getBody();
    }
     
     public ResponseListData<TrainerResponse> findTrainerByClassId(String id){
        ResponseEntity<ResponseListData<TrainerResponse>> response = restTemplate.exchange(url + "/class/trainer/" +id
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseListData<TrainerResponse>>(){} );

        return response.getBody();
    }
    
    public ResponseData<Influencer> getById(Long id){
        ResponseEntity<ResponseData<Influencer>> response = restTemplate.exchange(url + "/" + id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Influencer>>(){} );

        return response.getBody();
    }
    
//    public ResponseMessage<Employee> create(Employee data){
//        HttpEntity<Employee> entity = new HttpEntity(data);
//        
//        ResponseEntity<ResponseMessage<Employee>> response = restTemplate
//                .exchange(url, HttpMethod.POST, 
//                entity, new ParameterizedTypeReference<ResponseMessage<Employee>>(){} );
//
//        return response.getBody();
//    }
//    
//     public ResponseMessage<Employee> update(Long id, Employee data) {
//
//        data.setId(id);
//        HttpEntity entity = new HttpEntity(data);
//
//         ResponseEntity<ResponseMessage<Employee>> response = restTemplate
//                .exchange(url + "/" + id.toString(), HttpMethod.PUT, entity, new 
//        ParameterizedTypeReference<ResponseMessage<Employee>>() {});
//
//        return response.getBody();
//    }
    
//    public ResponseMessage<EmployeeRequest> create(EmployeeRequest data){
//        HttpEntity<EmployeeRequest> entity = new HttpEntity(data);
//        
//        ResponseEntity<ResponseMessage<EmployeeRequest>> response = restTemplate
//                .exchange(url, HttpMethod.POST, 
//                entity, new ParameterizedTypeReference<ResponseMessage<EmployeeRequest>>(){} );
//
//        return response.getBody();
//    }
    
    public ResponseMessage<EmployeeRequest> regist(EmployeeRequest data){
        HttpEntity<EmployeeRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<EmployeeRequest>> response = restTemplate
                .exchange(url + "/register", HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<EmployeeRequest>>(){} );

        return response.getBody();
    }

    public ResponseMessage<PesertaRequest> assignPeserta(PesertaRequest data){
        HttpEntity<PesertaRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<PesertaRequest>> response = restTemplate
                .exchange(url + "/assign_peserta", HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<PesertaRequest>>(){} );

        return response.getBody();
    }

    public ResponseMessage<AssignTrainerRequest> assignTrainer(AssignTrainerRequest data){
        HttpEntity<AssignTrainerRequest> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<AssignTrainerRequest>> response = restTemplate
                .exchange(url + "/assign_trainer", HttpMethod.POST, 
                entity, new ParameterizedTypeReference<ResponseMessage<AssignTrainerRequest>>(){} );

        return response.getBody();
    }
    
     public ResponseMessage<EmployeeRequest> update(Long id, EmployeeRequest data) {

        data.setId(id);
        HttpEntity entity = new HttpEntity(data);

         ResponseEntity<ResponseMessage<EmployeeRequest>> response = restTemplate
                .exchange(url + "/register/" + id.toString(), HttpMethod.PUT, entity, new 
        ParameterizedTypeReference<ResponseMessage<EmployeeRequest>>() {});

        return response.getBody();
    }
     
    public ResponseData<EmployeeResponse> findUserEmployeeById(Long id){
        ResponseEntity<ResponseData<EmployeeResponse>> response = restTemplate.exchange(url + "/register/" +id.toString()
            ,HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<EmployeeResponse>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Influencer> delete(Long id){
         
        ResponseEntity<ResponseMessage<Influencer>> response = restTemplate.exchange(url + "/" + id.toString()
            , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Influencer>>(){} );

        return response.getBody();
    }
    
    public ResponseMessage<Influencer> removeTrainerFromClass(Long trainerSubjetcId, Long employeeId){
         
        ResponseEntity<ResponseMessage<Influencer>> response = restTemplate.exchange(url + "/remove_trainer/"
                + trainerSubjetcId.toString() + "/" + employeeId.toString()
                , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Influencer>>(){} );

        return response.getBody();
    }

    public ResponseMessage<Influencer> removePesertaFromClass(String classId, Long employeeId){
         
        ResponseEntity<ResponseMessage<Influencer>> response = restTemplate.exchange(url + "/remove_peserta/"
                + classId + "/" + employeeId.toString()
                , HttpMethod.DELETE,null, new ParameterizedTypeReference<ResponseMessage<Influencer>>(){} );

        return response.getBody();
    }

    public ResponseMessage<ChangePasswordDTO> changePassword(ChangePasswordDTO data, String username){
        HttpEntity<ChangePasswordDTO> entity = new HttpEntity(data);
        
        ResponseEntity<ResponseMessage<ChangePasswordDTO>> response = restTemplate
                .exchange(url + "/change_password/" + username, HttpMethod.PUT, 
                entity, new ParameterizedTypeReference<ResponseMessage<ChangePasswordDTO>>(){} );

        return response.getBody();
    }
    
//    public ResponseMessage<ForgotPasswordRequest> forgotPassword(ForgotPasswordRequest data){
//        HttpEntity entity = new HttpEntity(data);
//        ResponseEntity<ResponseMessage<ForgotPasswordRequest>> response = restTemplate.exchange(url + "/register/forgot"
//            ,HttpMethod.POST, null, new ParameterizedTypeReference<ResponseMessage<ForgotPasswordRequest>>(){} );
//
//        return response.getBody();
//    }
    
}
