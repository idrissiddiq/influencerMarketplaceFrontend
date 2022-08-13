/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import java.util.Set;

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
import com.example.FinalProject.services.EmployeeService;
import com.example.FinalProject.services.JobService;
import com.example.FinalProject.services.KelasService;
import com.example.FinalProject.services.UserService;
import com.example.FinalProject.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;
    private UserService userService;
    private KelasService kelasService;
    private JobService jobService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, UserService userService, KelasService kelasService, JobService jobService) {
        this.employeeService = employeeService;
        this.userService = userService;
        this.kelasService = kelasService;
        this.jobService = jobService;
    }

    @GetMapping()
    public String index() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "admin/employee/cobaAjax";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/getAll")
    public @ResponseBody
    ResponseListData<Influencer> getAll() {
        return employeeService.getAll();
    }
    
    @GetMapping("/getAll/except")
    public @ResponseBody
    ResponseListData<Influencer> getAllExcept() {
        return employeeService.getAllExcept();
    }
    
    @GetMapping("/available_peserta")
    public @ResponseBody
    ResponseListData<Influencer> getAvailablePeserta() {
        return employeeService.findAllAvailablePeserta();
    }

    @GetMapping("/all_trainer")
    public @ResponseBody
    ResponseListData<Influencer> getAllTrainer() {
        return employeeService.findAllTrainer();
    }

    @GetMapping("/dashboard_admin")
    public @ResponseBody
    ResponseData<AdminDashboardDTO> getDashboardAdminInfo() {
        return employeeService.getDashboardAdminInfo();
    }

    @GetMapping("/dashboard_trainer")
    public @ResponseBody ResponseData<TrainerDashboardDTO> getDashboardTrainerInfo() {
        return employeeService.getDashboardTrainerInfo(GetAuthContext.getAuthorization().getName());
    }

    @GetMapping("/profile")
    public @ResponseBody ResponseData<ProfileDTO> getUserProfile() {
        return employeeService.getUserProfile(GetAuthContext.getAuthorization().getName());
    }

    @PutMapping("/change_password")
    public @ResponseBody
    ResponseMessage<ChangePasswordDTO> changePassword(@RequestBody ChangePasswordDTO data) {
        return employeeService.changePassword(data, GetAuthContext.getAuthorization().getName());
    }

    @GetMapping("/kelas/peserta/{id}")
    public @ResponseBody
    ResponseListData<Influencer> findEmployeeByClassId(@PathVariable("id") String id) {
        return employeeService.findEmployeeByClassId(id);
    }

    @GetMapping("/kelas/trainer/{id}")
    public @ResponseBody
    ResponseListData<TrainerResponse> findTrainerByClassId(@PathVariable("id") String id) {
        return employeeService.findTrainerByClassId(id);
    }

    @DeleteMapping("/remove_trainer/{trainerSubjectId}/{employeeId}")
    public @ResponseBody
    ResponseMessage<Influencer> removeTrainerFromClass(@PathVariable("employeeId") Long employeeId, @PathVariable("trainerSubjectId") Long trainerSubjectId) {
        return employeeService.removeTrainerFromClass(trainerSubjectId, employeeId);
    }

    @DeleteMapping("/remove_peserta/{classId}/{employeeId}")
    public @ResponseBody
    ResponseMessage<Influencer> removePesertaFromClass(@PathVariable("employeeId") Long employeeId, @PathVariable("classId") String classId) {
        return employeeService.removePesertaFromClass(classId, employeeId);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseMessage<EmployeeRequest> create(@RequestBody EmployeeRequest employee) {
        System.out.println("Bikinnn");
        return employeeService.regist(employee);
    }

    @PostMapping("/assign_peserta")
    public @ResponseBody
    ResponseMessage<PesertaRequest> assignPeserta(@RequestBody PesertaRequest employee) {
        return employeeService.assignPeserta(employee);
    }

    @PostMapping("/assign_trainer")
    public @ResponseBody
    ResponseMessage<AssignTrainerRequest> assignTrainer(@RequestBody AssignTrainerRequest employee) {
        return employeeService.assignTrainer(employee);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseData<Influencer> getById(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }

    @GetMapping("/user/{id}")
    public @ResponseBody
    ResponseData<EmployeeResponse> FindUserById(@PathVariable("id") Long id) {
        return employeeService.findUserEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody
    ResponseMessage<EmployeeRequest> update(@RequestBody EmployeeRequest employee, @PathVariable("id") Long id) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    ResponseMessage<Influencer> delete(@PathVariable("id") Long id) {
        return employeeService.delete(id);
    }

}
