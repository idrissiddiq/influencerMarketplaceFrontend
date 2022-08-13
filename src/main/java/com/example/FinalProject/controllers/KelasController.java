/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Kelas;
import com.example.FinalProject.models.response.KelasRequest;
import com.example.FinalProject.models.response.ResponseData;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.KelasService;
import com.example.FinalProject.services.SilabusService;
import com.example.FinalProject.utils.GetAuthContext;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/kelas")
public class KelasController {
    private KelasService kelasService;
    private SilabusService silabusService;

    @Autowired
    public KelasController(KelasService kelasService, SilabusService silabusService) {
        this.kelasService = kelasService;
        this.silabusService = silabusService;
    }

    @GetMapping
    public String index() {
        Authentication auth = GetAuthContext.getAuthorization();
        Set<String> roles = GetAuthContext.getAuthorityDetail(auth);
        if (roles.contains("ROLE_ADMIN")) {
            return "admin/kelas/index";
        }
        if (roles.contains("ROLE_TRAINER")) {
            return "trainer/kelas/daftarKelas";

        }
        return "redirect:/dashboard";
    }

    // kelas trainer
    @GetMapping("/trainer")
    public @ResponseBody ResponseListData<Kelas> getByTranscriptId() {
        return kelasService.findAllByTrainer();
    }

    // find by id
    @GetMapping("detail/{id}")
    public String findById(Model model, @PathVariable String id) {
        model.addAttribute("kelas", kelasService.getById(id));
        return "kelas/detail";
    }

    @GetMapping("/detail_ajax/{id}")
    public @ResponseBody ResponseData<Kelas> getById(@PathVariable("id") String id) {
        return kelasService.getById(id);
    }

    @GetMapping("/{id}")
    public String listKelas() {
        return "admin/kelas/list_kelas";
    }

    @GetMapping("/getAll")
    public @ResponseBody ResponseListData<Kelas> getAll() {
        return kelasService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseMessage<Kelas> delete(@PathVariable("id") String id) {
        return kelasService.delete(id);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseMessage<KelasRequest> update(@RequestBody KelasRequest request,
            @PathVariable("id") String id) {
        return kelasService.update(id, request);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseMessage<KelasRequest> create(@RequestBody KelasRequest request) {
        return kelasService.create(request);
    }

}
