package com.InfluencerMarketplace.clientapp.controllers;

import com.InfluencerMarketplace.clientapp.models.response.ResponseData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseListData;
import com.InfluencerMarketplace.clientapp.models.response.ResponseMessage;
import com.InfluencerMarketplace.clientapp.models.Influencer;
import com.InfluencerMarketplace.clientapp.models.InfluencerType;
import com.InfluencerMarketplace.clientapp.models.request.EditInfluencerProfileRequest;
import com.InfluencerMarketplace.clientapp.services.InfluencerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profile")
    public @ResponseBody
    ResponseData<Influencer> getMyProfile(){
        return influencerService.getMyProfileData();
    }

    @PostMapping("/profile/photo")
    public String getPhotoById(@RequestBody Long id, Model model) {
        model.addAttribute("photo", influencerService.getProfilePhotoById(id));

        return "Anonym/index";
    }

    @GetMapping("/type")
    public @ResponseBody
    ResponseListData<InfluencerType> getMyInfluenceType(){
        System.out.println("Keluar nih");
        return influencerService.getMyType();
    }

    @PutMapping("/profile")
    public @ResponseBody
    ResponseMessage<EditInfluencerProfileRequest> editMyProfile(@RequestBody EditInfluencerProfileRequest request) {
        return influencerService.editMyProfile(request);
    }


}
