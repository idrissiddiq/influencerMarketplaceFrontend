/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Silabus;
import com.example.FinalProject.models.Transcript;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.models.response.SSTSRapot;
import com.example.FinalProject.models.response.STSRapot;
import com.example.FinalProject.models.response.TranscriptData;
import com.example.FinalProject.services.SubSubjectTranscriptService;
import com.example.FinalProject.services.SubjectTranscriptService;
import com.example.FinalProject.services.TranscriptService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Idris Siddiq
 */
@Controller
@RequestMapping("/transcript")
public class TranscriptController {
    private TranscriptService transcriptService;
    private SubjectTranscriptService subjectTranscriptService;
    private SubSubjectTranscriptService subSubjectTranscriptService;

    @Autowired
    public TranscriptController(TranscriptService transcriptService, SubjectTranscriptService subjectTranscriptService, SubSubjectTranscriptService subSubjectTranscriptService) {
        this.transcriptService = transcriptService;
        this.subjectTranscriptService = subjectTranscriptService;
        this.subSubjectTranscriptService = subSubjectTranscriptService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("transcripts", transcriptService.getById());
        return "peserta/transcript/nilai";
    }
    
    @GetMapping("/rapot")
    public String rapot(Model model) {
        model.addAttribute("transcripts", transcriptService.getById());
        return "peserta/transcript/rapotPDF";
    }
    
    @GetMapping("/getAll")
    public @ResponseBody ResponseListData<Transcript> getAll() {
        return transcriptService.getAll();
    }

    //Rapot
    @GetMapping("/gradeAll")
    public @ResponseBody ResponseData<TranscriptData> getById() {
        return transcriptService.getById();
    }
    
    @GetMapping("/sts")
    public @ResponseBody ResponseListData<STSRapot> RapotSTS() {
        return subjectTranscriptService.RapotSTS();
    }
    
    @GetMapping("/ssts")
    public @ResponseBody ResponseListData<SSTSRapot> RapotSSTS() {
        return subSubjectTranscriptService.RapotSSTS();
    }
    
    
//
//     @GetMapping("/create")
//     public String create(Transcript transcript) {
//         return "grade/transcript";
//     }
//
//     @PostMapping("/create")
//     public String create(@Valid @ModelAttribute("transcript") Transcript transcript,
//     BindingResult result, RedirectAttributes attributes){
//
//         if(result.hasErrors()){
//             return "transcript/create";
//         }
//        
//         ResponseMessage<Transcript> response = transcriptService.create(transcript);
//         attributes.addFlashAttribute("message", response.getMessage());
//        
//         return "redirect:/transcript";
//     }
//
//     @GetMapping("/update/{id}")
//     public String update(@PathVariable("id") Long id, Model model) {
//         model.addAttribute("transcript",  transcriptService.getById(id).getData());
//         return "transcript/update";
//     }
//
//     @PutMapping("/update/{id}")
//     public String update(@PathVariable("id") Long id, 
//             @Valid @ModelAttribute("transcript") Transcript transcript, 
//             BindingResult result, RedirectAttributes redirectAttributes) {
//        
//         if (result.hasErrors()) {
//             return "transcript/update";
//         }
//        
//         ResponseMessage<Transcript> response = transcriptService.update(id, transcript);
//         redirectAttributes.addFlashAttribute("message", response.getMessage());
//        
//         return "redirect:/transcript";
//     }
//
//     @DeleteMapping("/delete/{id}")
//     public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        
//         ResponseMessage<Transcript> response = transcriptService.delete(id);
//         redirectAttributes.addFlashAttribute("message", response.getMessage());
//        
//         return "redirect:/transcript";
//     }
}
