package com.example.FinalProject.controllers;

import com.example.FinalProject.models.Campaign;
import com.example.FinalProject.models.Grade;
import com.example.FinalProject.models.Influencer;
import com.example.FinalProject.models.request.createCampaignRequest;
import com.example.FinalProject.models.response.ResponseListData;
import com.example.FinalProject.models.response.ResponseMessage;
import com.example.FinalProject.services.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/campaign")
public class CampaignController {
    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/getOnlyOpen")
    public @ResponseBody
    ResponseListData<Campaign> findAll(){
        return campaignService.findAllOnlyOpen();
    }

    @PostMapping("/create")
    public @ResponseBody ResponseMessage<createCampaignRequest> add(@RequestBody createCampaignRequest request) {
        System.out.println("CREATED");
        return  campaignService.create(request);
    }
}
