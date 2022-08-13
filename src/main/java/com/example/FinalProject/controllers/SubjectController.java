/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Subject;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SubjectDTO;
import com.example.FinalProject.models.response.SubjectRequest;
import com.example.FinalProject.services.SubjectService;
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
@RequestMapping("/subject")
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
 
    
    //trainer
    @GetMapping("/class/{id}")
   public @ResponseBody ResponseListData<SubjectDTO> getBySubjectTranscriptId(@PathVariable("id") String id) {
        return subjectService.findSubjectByClassId(id);
    }
   
   //admin
    @GetMapping("/admin/class/{id}")
    public @ResponseBody ResponseListData<SubjectDTO> findSubjectByClassId(@PathVariable("id") String id) {
        return subjectService.findSilabusByClassId(id);
    }
    
    @PostMapping("/create")
    public @ResponseBody ResponseMessage<SubjectRequest> create(@RequestBody SubjectRequest subject) {
        return subjectService.create(subject);
    }
    
    @GetMapping("/silabus/{id}")
    public @ResponseBody ResponseListData<Subject> findBySilabusId(@PathVariable("id") Long id) {
        return subjectService.findBySilabusId(id);
    }

    
    @GetMapping("/{id}")
    public @ResponseBody ResponseData<Subject> getById(@PathVariable("id") Long id) {
        return subjectService.getById(id);
    }
    
    @PutMapping("/update/{id}")
    public @ResponseBody ResponseMessage<SubjectRequest> update(@RequestBody SubjectRequest subject, @PathVariable("id") Long id) {
        return subjectService.update(id, subject);
    }
    
    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseMessage<Subject> delete(@PathVariable("id") Long id) {
        return subjectService.delete(id);
    }

    @GetMapping("/getAll")
    public @ResponseBody ResponseListData<Subject> getAll() {
        return subjectService.getAll();
    }
}
