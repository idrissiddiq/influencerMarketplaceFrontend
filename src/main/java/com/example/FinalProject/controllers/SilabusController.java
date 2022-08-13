/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import java.util.Set;

import com.example.FinalProject.models.Silabus;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.SilabusService;
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

@Controller
@RequestMapping("/silabus")
public class SilabusController {
    private SilabusService silabusService;

    @Autowired
    public SilabusController(SilabusService silabusService) {
        this.silabusService = silabusService;
    }

    @GetMapping
    public String index() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "admin/silabus/index";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/subject/{id}")
    public String detailSubject() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "admin/subject/index";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/subject/subsubject/{id}")
    public String detailSubMateri() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_ADMIN")) {
            return "admin/subsubject/index";
        }
        return "redirect:/dashboard";

    }

    @GetMapping("/getAll")
    public @ResponseBody ResponseListData<Silabus> getAll() {
        return silabusService.getAll();
    }

    @PostMapping("/create")
    public @ResponseBody ResponseMessage<Silabus> create(@RequestBody Silabus silabus) {
        return silabusService.create(silabus);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseData<Silabus> getById(@PathVariable("id") Long id) {
        return silabusService.getById(id);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseMessage<Silabus> update(@RequestBody Silabus silabus, @PathVariable("id") Long id) {
        return silabusService.update(id, silabus);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseMessage<Silabus> delete(@PathVariable("id") Long id) {
        return silabusService.delete(id);
    }

}
