/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import java.util.Set;

import com.example.FinalProject.models.SubSubjectTranscript;
import com.example.FinalProject.models.response.NilaiSSTS;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.STSRequest;
import com.example.FinalProject.models.response.SubSTSTrainer;
import com.example.FinalProject.services.SubSubjectTranscriptService;
import com.example.FinalProject.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Idris Siddiq
 */
@Controller
@RequestMapping("/subsubject_transcript")
public class SubSubjectTranscriptController {
    private SubSubjectTranscriptService subSubjectTranscriptService;

    @Autowired
    public SubSubjectTranscriptController(SubSubjectTranscriptService subSubjectTranscriptService) {
        this.subSubjectTranscriptService = subSubjectTranscriptService;
    }

    @GetMapping("transcript/{id}")
    public String index() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_PESERTA")) {
            return "peserta/subjectTranscript/nilaiSubSubject";
        }
        return "redirect:/dashboard";
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseListData<SubSubjectTranscript> getBySubjectTranscriptId(@PathVariable("id") Long id) {
        return subSubjectTranscriptService.getBySubjectTranscriptId(id);
    }

    // trainer
    @GetMapping("kelas/{classid}/{id}")
    public String trainer() {
        return "trainer/subSubjectTranscript/nilaiSSTS";
    }

    @GetMapping("/{classid}/{id}")
    public @ResponseBody ResponseListData<SubSTSTrainer> findSSTSBySubject(@PathVariable("classid") String classid,
            @PathVariable("id") Long id) {
        return subSubjectTranscriptService.findSSTSBySubject(classid, id);
    }

    // edit nilai trainer
    // index
    @GetMapping("/trainer/edit/{classid}/{id}")
    public String editNilai() {
        return "trainer/subSubjectTranscript/editNilaiSSTS";
    }

    // getall
    @GetMapping("edit/{classid}/{id}")
    public @ResponseBody ResponseListData<NilaiSSTS> findAllBySubSubject(@PathVariable("classid") String classid,
            @PathVariable("id") Long id) {
        return subSubjectTranscriptService.findAllBySubSubject(classid, id);
    }

    // find by id
    @GetMapping("/id/{id}")
    public @ResponseBody ResponseData<SubSubjectTranscript> update(@PathVariable("id") Long id) {
        return subSubjectTranscriptService.getById(id);
    }

    // Update nilai
    @PutMapping("/inputnilai")
    public @ResponseBody ResponseMessage<STSRequest> updateNilai(@RequestBody STSRequest req) {
        return subSubjectTranscriptService.updateNilai(req);
    }

    // @GetMapping("/create")
    // public String create(SubSubjectTranscript subSubjectTranscript) {
    // return "grade/subsubject_transcript";
    // }
    //
    // @PostMapping("/create")
    // public String create(@Valid @ModelAttribute("subsubject_transcript")
    // SubSubjectTranscript subSubjectTranscript,
    // BindingResult result, RedirectAttributes attributes){
    //
    // if(result.hasErrors()){
    // return "subsubject_transcript/create";
    // }
    //
    // ResponseMessage<SubSubjectTranscript> response =
    // subSubjectTranscriptService.create(subSubjectTranscript);
    // attributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subsubject_transcript";
    // }
    //
    // @GetMapping("/update/{id}")
    // public String update(@PathVariable("id") Long id, Model model) {
    // model.addAttribute("subsubject_transcript",
    // subSubjectTranscriptService.getById(id).getData());
    // return "subsubject_transcript/update";
    // }
    //
    // @PutMapping("/update/{id}")
    // public String update(@PathVariable("id") Long id,
    // @Valid @ModelAttribute("subsubject_transcript") SubSubjectTranscript
    // subSubjectTranscript,
    // BindingResult result, RedirectAttributes redirectAttributes) {
    //
    // if (result.hasErrors()) {
    // return "subsubject_transcript/update";
    // }
    //
    // ResponseMessage<SubSubjectTranscript> response =
    // subSubjectTranscriptService.update(id, subSubjectTranscript);
    // redirectAttributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subsubject_transcript";
    // }
    //
    // @DeleteMapping("/delete/{id}")
    // public String delete(@PathVariable("id") Long id, RedirectAttributes
    // redirectAttributes) {
    //
    // ResponseMessage<SubSubjectTranscript> response =
    // subSubjectTranscriptService.delete(id);
    // redirectAttributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subsubject_transcript";
    // }
}
