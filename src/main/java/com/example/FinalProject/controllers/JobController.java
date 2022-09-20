/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Job;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping("/job")
public class JobController {
    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }
    //Get all
    @GetMapping("/getAll")
    public @ResponseBody ResponseListData<Job> getAll(){
        return jobService.getAll();
    }
    
    @GetMapping("/getAll/except")
    public @ResponseBody ResponseListData<Job> getAllExcept(){
        return jobService.getAllExcept();
    }
}
