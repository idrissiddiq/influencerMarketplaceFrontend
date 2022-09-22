package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.services.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/influencer")
public class InfluencerController {
    private InfluencerService influencerService;

    @Autowired
    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    @GetMapping("/getAll")
    public @ResponseBody
    ResponseListData<Influencer> findAll(){
        return influencerService.findAll();
    }


}
