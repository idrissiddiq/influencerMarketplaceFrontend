/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.SubSubject;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SubSubjectDTO;
import com.example.FinalProject.models.response.SubSubjectRequest;
import com.example.FinalProject.services.SubSubjectService;

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
 * @author Idris Siddiq
 */
@Controller
@RequestMapping("/subsubject")
public class SubSubjectController {

    private SubSubjectService subSubjectService;

    @Autowired
    public SubSubjectController(SubSubjectService subSubjectService) {
        this.subSubjectService = subSubjectService;
    }

    @GetMapping("/subject/{id}")
    public @ResponseBody ResponseListData<SubSubject> findBySubjectId(@PathVariable("id") Long id) {
        return subSubjectService.findBySubjectId(id);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseData<SubSubject> getById(@PathVariable("id") Long id) {
        return subSubjectService.getById(id);
    }
    
   //trainer
    @GetMapping("/subject_Trainer/{id}")
   public @ResponseBody ResponseListData<SubSubjectDTO> findsSubSubjectBySubjectId(@PathVariable("id") Long classId) {
        return subSubjectService.findsSubSubjectBySubjectId(classId);
    }
   @PutMapping("/update/{id}")
   public @ResponseBody ResponseMessage<SubSubjectRequest> update(@RequestBody SubSubjectRequest subsubject, @PathVariable("id") Long id) {
       return subSubjectService.update(id, subsubject);
   }

   @PostMapping("/create")
    public @ResponseBody ResponseMessage<SubSubjectRequest> create(@RequestBody SubSubjectRequest data) {
        return subSubjectService.create(data);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseMessage<SubSubject> delete(@PathVariable("id") Long id) {
        return subSubjectService.delete(id);
    }
}
