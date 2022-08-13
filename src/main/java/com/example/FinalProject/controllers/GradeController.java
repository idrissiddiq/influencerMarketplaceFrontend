/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Grade;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.GradeService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Idris Siddiq
 */
@Controller
@RequestMapping("/grade")
public class GradeController {
    private GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public String index() {
        return "index";
    }


     @GetMapping("/create")
     public String create(Grade grade) {
         return "grade/create";
     }

     @PostMapping("/create")
     public String create(@Valid @ModelAttribute("grade") Grade grade,
     BindingResult result, RedirectAttributes attributes){

         if(result.hasErrors()){
             return "grade/create";
         }
        
         ResponseMessage<Grade> response = gradeService.create(grade);
         attributes.addFlashAttribute("message", response.getMessage());
        
         return "redirect:/grade";
     }

     @GetMapping("/update/{id}")
     public String update(@PathVariable("id") String id, Model model) {
         model.addAttribute("grade",  gradeService.getById(id).getData());
         return "grade/update";
     }

     @PutMapping("/update/{id}")
     public String update(@PathVariable("id") String id, 
             @Valid @ModelAttribute("grade") Grade grade, 
             BindingResult result, RedirectAttributes redirectAttributes) {
        
         if (result.hasErrors()) {
             return "grade/update";
         }
        
         ResponseMessage<Grade> response = gradeService.update(id, grade);
         redirectAttributes.addFlashAttribute("message", response.getMessage());
        
         return "redirect:/grade";
     }

     @DeleteMapping("/delete/{id}")
     public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        
         ResponseMessage<Grade> response = gradeService.delete(id);
         redirectAttributes.addFlashAttribute("message", response.getMessage());
        
         return "redirect:/Grade";
     }
}
