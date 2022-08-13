/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import java.util.Set;

import com.example.FinalProject.models.SubjectTranscript;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.SubjectTranscriptTrainer;
import com.example.FinalProject.services.SubjectTranscriptService;
import com.example.FinalProject.utils.GetAuthContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Idris Siddiq
 */
@Controller
@RequestMapping("/subject_transcript")
public class SubjectTranscriptController {
    private SubjectTranscriptService subjectTranscriptService;

    @Autowired
    public SubjectTranscriptController(SubjectTranscriptService subjectTranscriptService) {
        this.subjectTranscriptService = subjectTranscriptService;
    }

    @GetMapping()
    public String peserta() {
        Set<String> roles = GetAuthContext.getAuthorityDetail(GetAuthContext.getAuthorization());
        if (roles.contains("ROLE_PESERTA")) {
            return "peserta/subjectTranscript/nilaiSubject";
        }
        return "redirect:/dashboard";
    }

    // peserta
    @GetMapping("/transcript")
    public @ResponseBody ResponseListData<SubjectTranscript> getByTranscriptId() {
        return subjectTranscriptService.getByTranscriptId();
    }

    // trainer
    @GetMapping("/class/{id}")
    public String trainer() {
        return "trainer/subjectTranscript/nilaiSubject";
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseListData<SubjectTranscriptTrainer> getBySubjectTranscriptId(
            @PathVariable("id") String id) {
        return subjectTranscriptService.findSTSByClass(id);
    }

    //
    // @GetMapping("/create")
    // public String create(SubjectTranscript subjectTranscript) {
    // return "grade/subject_transcript";
    // }
    //
    // @PostMapping("/create")
    // public String create(@Valid @ModelAttribute("subsubject_transcript")
    // SubjectTranscript subjectTranscript,
    // BindingResult result, RedirectAttributes attributes){
    //
    // if(result.hasErrors()){
    // return "subject_transcript/create";
    // }
    //
    // ResponseMessage<SubjectTranscript> response =
    // subjectTranscriptService.create(subjectTranscript);
    // attributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subject_transcript";
    // }
    //
    // @GetMapping("/update/{id}")
    // public String update(@PathVariable("id") Long id, Model model) {
    // model.addAttribute("subject_transcript",
    // subjectTranscriptService.getById(id).getData());
    // return "subject_transcript/update";
    // }
    //
    // @PutMapping("/update/{id}")
    // public String update(@PathVariable("id") Long id,
    // @Valid @ModelAttribute("subject_transcript") SubjectTranscript
    // subjectTranscript,
    // BindingResult result, RedirectAttributes redirectAttributes) {
    //
    // if (result.hasErrors()) {
    // return "subject_transcript/update";
    // }
    //
    // ResponseMessage<SubjectTranscript> response =
    // subjectTranscriptService.update(id, subjectTranscript);
    // redirectAttributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subject_transcript";
    // }
    //
    // @DeleteMapping("/delete/{id}")
    // public String delete(@PathVariable("id") Long id, RedirectAttributes
    // redirectAttributes) {
    //
    // ResponseMessage<SubjectTranscript> response =
    // subjectTranscriptService.delete(id);
    // redirectAttributes.addFlashAttribute("message", response.getMessage());
    //
    // return "redirect:/subject_transcript";
    // }
}
